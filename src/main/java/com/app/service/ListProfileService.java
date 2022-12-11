package com.app.service;

import com.app.dto.request.ListProfileRequest;
import com.app.dto.response.ListProfileResponseType;

public interface ListProfileService {
    public ListProfileResponseType save(ListProfileRequest listProfileRequest);

    public Boolean delete(ListProfileRequest listProfileResponseType);
}
