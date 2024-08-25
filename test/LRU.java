package test;


import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class LRU implements CacheReplacementPolicy {
    HashSet<String> cache=new HashSet<>();
    @Override
    public void add(String word) {
        if(cache.contains(word))
            cache.remove(word);
        else cache.add(word);
    }

    @Override
    public String remove() {
        String[] stringsArr=new String[cache.size()];
        stringsArr=cache.toArray(stringsArr);
        return stringsArr[0];
    }

}
