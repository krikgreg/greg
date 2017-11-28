package com.nixsolutions.laboratoryeighteen.entity.converter;

import java.sql.Date;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = false)
public class StringSqlDateConverter implements AttributeConverter<String, Date> {
	
    @Override
    public Date convertToDatabaseColumn(String locString) {
    	return (locString == null ? null : Date.valueOf(locString));
    }

    @Override
    public String convertToEntityAttribute(Date sqlDate) {
    	return (sqlDate == null ? null : sqlDate.toString());
    }
}