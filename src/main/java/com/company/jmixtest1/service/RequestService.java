package com.company.jmixtest1.service;

import com.company.jmixtest1.InputDataSplitter;
import com.company.jmixtest1.MagicSquare;
import com.company.jmixtest1.SubstringSearch;
import com.company.jmixtest1.entity.Request;
import com.company.jmixtest1.pojo.RequestResult;
import io.jmix.core.DataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static com.company.jmixtest1.entity.TaskType.MAGIC_SQUARE;
import static com.company.jmixtest1.entity.TaskType.SUBSTRING_SEARCH;

@Service("RequestService")
public class RequestService {
    @Autowired
    private DataManager dataManager;

    public Request save(Request request) {
        Request requestResult = dataManager.create(Request.class);
        requestResult.setInputData(request.getInputData());
        requestResult.setTaskType(request.getTaskType());

        return dataManager.save(requestResult);
    }

    public Request loadById(UUID id) {
        return dataManager.load(Request.class)
                .id(id)
                .one();
    }

    public List<Request> loadAll() {
        return dataManager.load(Request.class).all().list();
    }

    public RequestResult calculate(Request request) {
        RequestResult result = new RequestResult();
        try {
            if (request.getTaskType() == SUBSTRING_SEARCH) {
                String[] input = InputDataSplitter.splitInput(request.getInputData());
                if (input.length == 2) {
                    String[] array1 = InputDataSplitter.stringInArray(input[0]);
                    String[] array2 = InputDataSplitter.stringInArray(input[1]);
                    List<String> res = SubstringSearch.search(array1, array2);
                    result.setResultMessage(String.valueOf(res));
                }
                else {
                    result.setResultMessage("error, bad input");
                }
            }
            else if (request.getTaskType() == MAGIC_SQUARE) {
                String[] array = InputDataSplitter.stringInArray(request.getInputData());
                if (array.length == 9) {
                    int[] intArray = Stream.of(array)
                            .mapToInt(Integer::parseInt)
                            .toArray();
                    List<String> res = Collections.singletonList(Arrays.deepToString(MagicSquare.find(intArray)));
                    result.setResultMessage(String.valueOf(res));
                }
                else {
                    result.setResultMessage("bad input");
                }
            }
            else {
                result.setResultMessage("command not found");
            }
        }
        catch (NumberFormatException e) {
            result.setResultMessage(e.getMessage());
        }
        return result;
    }

    public RequestResult export(Request request) {
        RequestResult result = new RequestResult();
        try {
            JAXBContext context = JAXBContext.newInstance(Request.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(request, new File("./request-out.xml"));
            result.setResultMessage("file uploaded successfully");
        } catch (JAXBException e) {
            result.setResultMessage("file upload failed");
        }
        return result;
    }

    public Request import_(MultipartFile input) throws IOException, JAXBException {
        String fileContent = new String(input.getBytes());
        JAXBContext context = JAXBContext.newInstance(Request.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        StringReader reader = new StringReader(fileContent);
        return (Request) unmarshaller.unmarshal(reader);
    }
}