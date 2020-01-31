package com.bysssss.goinmul.api.core.contents.spec;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.bysssss.goinmul.api.exception.GoinmulException;
import com.bysssss.goinmul.api.model.mysql.contents.data.ContentsData;
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
public class ContentsPostRequest implements IRequest {
	@NotNull
	@Min(100000L)
    private Long memberSeq;
	
	@NotBlank
	@Size(min = 1, max = 128)
    private String contentsTitle;
	
	private String contentsDesc;
	
	private Integer testInt;
	private Double testDbl;
	
	public ContentsData takeContents() {
		return ContentsData.builder()
				.memberSeq(this.memberSeq)
				.contentsTitle(this.contentsTitle)
				.contentsDesc(this.contentsDesc)
				.testInt(this.testInt)
				.testDbl(this.testDbl)
				.build();
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
