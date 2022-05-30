package software.xdev.vaadin.maps.leaflet.flow.data.options;

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
}
