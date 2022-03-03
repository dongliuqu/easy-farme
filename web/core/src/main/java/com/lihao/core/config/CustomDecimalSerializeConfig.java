package com.lihao.core.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.lihao.core.anno.CustomDecimalSerialize;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Objects;

public class CustomDecimalSerializeConfig extends JsonSerializer<BigDecimal> implements ContextualSerializer {

    private DecimalFormat decimalFormat;

    private String reduction;

    public CustomDecimalSerializeConfig() {
    }

    public CustomDecimalSerializeConfig(DecimalFormat decimalFormat, String reduction) {

        this.decimalFormat = decimalFormat;
        this.reduction = reduction;
    }

    @Override
    public void serialize(BigDecimal bigDecimal, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        BigDecimal returnStr = null;
        if (Objects.isNull(bigDecimal)) {
            jsonGenerator.writeNull();
        } else {
            returnStr = bigDecimal.divide(new BigDecimal(reduction), RoundingMode.HALF_UP).setScale(6, RoundingMode.HALF_UP);
        }
        jsonGenerator.writeString(decimalFormat.format(returnStr));
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        if (beanProperty != null) {
            if (Objects.equals(beanProperty.getType().getRawClass(), BigDecimal.class)) {
                CustomDecimalSerialize annotation = beanProperty.getAnnotation(CustomDecimalSerialize.class);
                if (Objects.nonNull(annotation)) {
                    int scale = annotation.scale();
                    DecimalFormat decimalFormat = new DecimalFormat();
                    decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("0");
                    if (scale > 1) {
                        stringBuilder.append(".");
                        for (int i = 0; i < annotation.scale(); i++) {
                            stringBuilder.append("0");
                        }
                    }
                    decimalFormat.applyPattern(stringBuilder.toString());
                    return new CustomDecimalSerializeConfig(decimalFormat, annotation.reduction());
                }
            }
            return serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);
        }
        return serializerProvider.findNullValueSerializer(beanProperty);
    }
}
