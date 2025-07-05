package com.codesmell.config.audit;

import java.time.Instant;

public interface Auditable {

    void setCreatedDate(Instant createdDate);

    void setUpdatedDate(Instant updatedDate);

    void setCreatedBy(String createdBy);

    void setUpdatedBy(String updatedBy);

}
