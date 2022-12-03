package com.app.service;

import com.app.dto.response.ListProfileResponseType;

public interface ListProfileService {
    public Boolean save(ListProfileResponseType listProfileResponseType);

    public Boolean delete(ListProfileResponseType listProfileResponseType);
}
