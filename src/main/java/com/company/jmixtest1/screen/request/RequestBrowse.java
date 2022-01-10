package com.company.jmixtest1.screen.request;

import io.jmix.ui.screen.*;
import com.company.jmixtest1.entity.Request;

@UiController("Request.browse")
@UiDescriptor("request-browse.xml")
@LookupComponent("requestsTable")
public class RequestBrowse extends StandardLookup<Request> {
}