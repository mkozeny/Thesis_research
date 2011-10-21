@Target(ElementType.FIELD)
public @interface Length {
    int max();
    int min();
}