package com.josh.joinus.storage.db.core;

import com.josh.joinus.core.domain.Position;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.EnumSet;

@Converter
public class PositionConverter implements AttributeConverter<EnumSet<Position>, String> {
    @Override
    public String convertToDatabaseColumn(EnumSet<Position> attribute) {
        if (attribute == null || attribute.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (Position position : attribute) {
            sb.append(position.name()).append(",");
        }

        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    @Override
    public EnumSet<Position> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return null;
        }
        String[] positions = dbData.split(",");
        EnumSet<Position> positionSet = EnumSet.noneOf(Position.class);
        for (String position : positions) {
            positionSet.add(Position.valueOf(position));
        }
        return positionSet;
    }
}
