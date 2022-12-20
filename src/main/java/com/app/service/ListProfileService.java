package com.app.service;

import com.app.dto.request.ListProfileRequest;
import com.app.dto.response.ListProfileResponseSaveType;

public interface ListProfileService {
    public ListProfileResponseSaveType save(ListProfileRequest listProfileRequest);

    public Boolean delete(ListProfileRequest listProfileResponseType);
}
