package com.sdc.kafka.service.wmd.dto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.Setter;

@Getter
@Setter
@SuperBuilder
public class UserDTO {

	protected String userId;

	protected String userName;
}
