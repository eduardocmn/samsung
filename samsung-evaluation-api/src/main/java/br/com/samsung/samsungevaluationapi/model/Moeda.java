package br.com.samsung.samsungevaluationapi.model;

import lombok.Data;

@Data
public class Moeda {
	private Long currencyId;
    private String currencyCode;
    private String currencyDesc; 

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currencyId == null) ? 0 : currencyId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Moeda other = (Moeda) obj;
		if (currencyId == null) {
			if (other.currencyId != null)
				return false;
		} else if (!currencyId.equals(other.currencyId))
			return false;
		return true;
	}
 }
