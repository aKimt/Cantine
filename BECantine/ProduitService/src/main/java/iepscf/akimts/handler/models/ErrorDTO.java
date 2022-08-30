package iepscf.akimts.handler.models;

import iepscf.akimts.handler.annotation.AdvisorHandled;
import iepscf.akimts.handler.annotation.SkippedProperty;

import java.beans.FeatureDescriptor;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ErrorDTO extends HashMap<String, Object> {
    public ErrorDTO(String message) {
        this.put("message", message);
        this.put("timestamp", Instant.now());
    }

    public ErrorDTO(String message, Map<? extends String, ?> info) {
        super(info);
        this.put("message", message);
        this.put("timestamp", Instant.now());
    }

    public static ErrorDTO of(Throwable ex) {
        AdvisorHandled annotation = ex.getClass().getAnnotation(AdvisorHandled.class);
        return annotation != null ? of(ex, annotation.skipFrom()) : of(ex, Throwable.class);
    }

    public static ErrorDTO of(Throwable ex, Class<? extends Throwable> skipFrom) {
        HashMap<String, Object> info = new HashMap<>();

        try {
            PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(ex.getClass()).getPropertyDescriptors();
            List<String> toSkip = getSkippedPropertiesName(skipFrom);

            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                boolean isSkipped = false;

                for (int j = 0; !isSkipped && j < toSkip.size(); ++j) {
                    if ( toSkip.get(j).equals(propertyDescriptor.getName()) ) {
                        isSkipped = true;
                        toSkip.remove(toSkip.get(j));
                    }
                }

                if (!isSkipped) {
                    try {
                        boolean skippedPropertyFromField = ex.getClass().getDeclaredField(propertyDescriptor.getName()).isAnnotationPresent(SkippedProperty.class);
                        boolean skippedPropertyFromMethod = propertyDescriptor.getReadMethod().isAnnotationPresent(SkippedProperty.class);
                        if (!skippedPropertyFromField && !skippedPropertyFromMethod) {
                            info.put(propertyDescriptor.getName(), propertyDescriptor.getReadMethod().invoke(ex));
                        }
                    } catch (IllegalAccessException | NoSuchFieldException | InvocationTargetException ignored) {
                    }
                }
            }
        } catch (IntrospectionException ignored) {}

        return new ErrorDTO(ex.getMessage(), info);
    }

    public static List<String> getSkippedPropertiesName(Class<? extends Throwable> from) throws IntrospectionException {
        return Arrays.stream(Introspector.getBeanInfo(from).getPropertyDescriptors()).map(FeatureDescriptor::getName).collect(Collectors.toList());
    }
}