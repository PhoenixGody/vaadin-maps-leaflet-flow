package software.xdev.vaadin.maps.leaflet.flow.data.options;

import java.util.Objects;

public class LeafletOptionsContainerKey<T> {
    private final Class<T> classType;
    private final String name;

    public LeafletOptionsContainerKey(Class<T> classType, String name)
    {
        this.classType = classType;
        this.name = name;
    }

    public Class<T> getClassType()
    {
        return classType;
    }

    public String getName()
    {
        return name;
    }

    @Override
    public String toString()
    {
        return "[classType=" + getClassType() + ", name=" + getName() + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LeafletOptionsContainerKey<?> that = (LeafletOptionsContainerKey<?>) o;
        // only name because the name should be the unique identifier
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        // only name because the name should be the unique identifier
        return Objects.hash(name);
    }
}
