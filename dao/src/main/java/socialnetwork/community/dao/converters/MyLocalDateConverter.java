package socialnetwork.community.dao.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;
import java.time.LocalDate;


@Converter(autoApply = true)
public class MyLocalDateConverter implements AttributeConverter<LocalDate, Date> {
	public Date convertToDatabaseColumn(LocalDate localDate) {
		return localDate == null ? null : Date.valueOf(localDate);
	}

	public LocalDate convertToEntityAttribute(Date date) {
		return date == null ? null : date.toLocalDate();
	}
}