package software.xdev.vaadin.maps.leaflet.flow.data.options;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LeafletOptionsContainer implements Map<LeafletOptionsContainerKey<?>, Object> {
    private Map<LeafletOptionsContainerKey<?>, Object> delegateContainer;

    public LeafletOptionsContainer() {
        delegateContainer = new HashMap<>();
    }

    @Nullable
    public <T> T getTyped(LeafletOptionsContainerKey<T> key)
    {
        Object value = delegateContainer.get(key.getName());
        if (value == null)
        {
            return null;
        }

        return key.getClassType().cast(value);
    }

    public <T> T putTyped(LeafletOptionsContainerKey<T> key, T value)
    {
        T castedValue = key.getClassType().cast(value);
        Object result =  delegateContainer.put(key, castedValue);
        if (result == null)
            return null;

        return key.getClassType().cast(result);
    }


    @Override
    public Object remove(Object key) {
        if (!(key instanceof LeafletOptionsContainerKey<?>))
            throw new ClassCastException();

        return this.remove((LeafletOptionsContainerKey<?>) key);
    }

    public Object remove(LeafletOptionsContainerKey<?> key)
    {
        return delegateContainer.remove(key);
    }

    @Override
    public int size() {
        return delegateContainer.size();
    }

    @Override
    public boolean isEmpty() {
        return delegateContainer.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        if (!(key instanceof LeafletOptionsContainerKey<?>))
            throw new ClassCastException();

        return this.containsKey((LeafletOptionsContainerKey<?>) key);
    }

    public boolean containsKey(LeafletOptionsContainerKey<?> key) {
        return delegateContainer.containsKey(key.getName());
    }

    @Override
    public boolean containsValue(Object value) {
        return delegateContainer.containsValue(value);
    }

    @Override
    public void clear() {
        delegateContainer.clear();
    }

    @Override
    public Set<Map.Entry<LeafletOptionsContainerKey<?>, Object>> entrySet(){
        return delegateContainer.entrySet();
    }

    @Override
    public Object get(Object key) {
        if (!(key instanceof LeafletOptionsContainerKey<?>))
            throw new ClassCastException();

        return this.getTyped((LeafletOptionsContainerKey<?>) key);
    }

    @NotNull
    @Override
    public Set<LeafletOptionsContainerKey<?>> keySet() {
        return delegateContainer.keySet();
    }

    @NotNull
    @Override
    public Collection<Object> values() {
        return delegateContainer.values();
    }

    @Override
    public boolean equals(Object o) {
        return delegateContainer.equals(o);
    }

    @Override
    public int hashCode() {
        return delegateContainer.hashCode();
    }


    @Nullable
    @Override
    public Object put(LeafletOptionsContainerKey<?> key, Object value) {
        return delegateContainer.put(key, value);
    }

    @Override
    public void putAll(@NotNull Map<? extends LeafletOptionsContainerKey<?>, ?> m) {
        delegateContainer.putAll(m);
    }
}
