package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Badge {
    @JsonProperty("votes")
    private int votes;
    @JsonProperty("attachmentsByType")
    AttachmentByType attachments;
}
