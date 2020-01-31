package com.bysssss.goinmul.api.core.contents.spec;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.bysssss.goinmul.api.exception.GoinmulException;
import com.bysssss.goinmul.api.spec.IRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ContentsGetRequest implements IRequest {
	@NotNull
	@Min(1L)
    private Long seq;

	@Override
	public void check() throws GoinmulException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toLogging() {
		// TODO Auto-generated method stub
		return null;
	}
}
