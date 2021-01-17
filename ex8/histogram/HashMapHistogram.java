package Exercises.ex8.histogram;

import java.util.*;

/**************************************
 *  Add your code to this class !!!   *
 **************************************/
public class HashMapHistogram<T extends Comparable<T>> implements IHistogram<T> {

    private final HashMap<T, Integer> map;

    public HashMapHistogram() {
        this.map = new HashMap<>();

    }

    @Override
    public Iterator<T> iterator() {
        return new HashMapHistogramIterator<>(this.map);
    }

    @Override
    public void addItem(T item) {
        if (this.map.containsKey(item)) {
            this.map.put(item, this.map.get(item) + 1);
        } else {
            this.map.put(item, 1);
        }
    }


    @Override
    public void removeItem(T item) throws IllegalItemException {
        if (!this.map.containsKey(item)) {
            throw new IllegalItemException();
        }
        this.map.put(item, this.map.get(item) - 1);
        if (this.map.get(item) == 0){
            this.map.remove(item);
        }
    }


    @Override
    public void addItemKTimes(T item, int k) throws IllegalKValueException {
    if (k<0){
        throw new IllegalKValueException(k);
    }
    if (!this.map.containsKey(item)){
        this.map.put(item,k);
    }else{
        this.map.put(item, this.map.get(item) + k);
    }
    }

    @Override
    public void removeItemKTimes(T item, int k) throws IllegalItemException, IllegalKValueException {
        if (!this.map.containsKey(item)) {
            throw new IllegalItemException();
        }
        if (k<0 || this.map.get(item) < k){
            throw  new IllegalKValueException(k);
        }
        this.map.put(item, this.map.get(item) - k);
    }

    @Override
    public int getCountForItem(T item) {
        if (!this.map.containsKey(item)){
        return 0;
        }
        return this.map.get(item);
    }

    @Override
    public void addAll(Collection<T> items) {
        for (T item: items){
            addItem(item);
        }
    }

    @Override
    public void clear() {
        this.map.clear();
    }

    @Override
    public Set<T> getItemsSet() {
        Set<T> set = new HashSet<>();
        for (T key : this.map.keySet()){
            if (this.map.get(key) > 0){
                set.add(key);
            }
        }
        return set;
    }

    @Override
    public void update(IHistogram<T> anotherHistogram) {
        for (T item: anotherHistogram.getItemsSet()){
            if (this.map.containsKey(item)){
                this.map.put(item, this.map.get(item) + anotherHistogram.getCountForItem(item));
            }else{
                this.map.put(item,anotherHistogram.getCountForItem(item));
            }
        }
    }

}
