package com.bysssss.goinmul.api.core.contents.spec;

import java.util.Date;

import javax.validation.constraints.Min;

import com.bysssss.goinmul.api.common.util.DateUtil;
import com.bysssss.goinmul.api.common.util.NumsUtil;
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
public class ContentsAllGetRequest implements IRequest {
	@Min(1000000000000L)
	private String statusAt;
	
	public Date takeHourlyStatusAt() {
		if( this.statusAt == null) {
			return null;
		}
		
		Long timestamp = NumsUtil.toLong(this.statusAt);
		return DateUtil.toHourly(DateUtil.toDate(timestamp));
	}

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
