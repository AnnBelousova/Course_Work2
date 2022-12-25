public class Validation {
    String field;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void throwExceptionStringField(String str) {
        if (str == "" || str.isEmpty() || str.isBlank()) {
            throw new IllegalArgumentException("Это поле обязательно для заполнения или некорректно заполнено");
        } else {
        }
    }
}
