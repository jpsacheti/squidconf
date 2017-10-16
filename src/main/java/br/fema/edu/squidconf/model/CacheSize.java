package br.fema.edu.squidconf.model;


public class CacheSize {

    private Integer diskCacheSize;
    private Integer memoryCacheSize;

    public CacheSize(Integer diskCacheSize, Integer memoryCacheSize) {
        this.diskCacheSize = diskCacheSize;
        this.memoryCacheSize = memoryCacheSize;
    }

    public CacheSize() {
    }

    public Integer getDiskCacheSize() {
        return diskCacheSize;
    }

    public void setDiskCacheSize(Integer diskCacheSize) {
        this.diskCacheSize = diskCacheSize;
    }

    public Integer getMemoryCacheSize() {
        return memoryCacheSize;
    }

    public void setMemoryCacheSize(Integer memoryCacheSize) {
        this.memoryCacheSize = memoryCacheSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CacheSize)) return false;

        CacheSize cacheSize = (CacheSize) o;

        return (diskCacheSize != null ? diskCacheSize.equals(cacheSize.diskCacheSize) : cacheSize.diskCacheSize == null) && (memoryCacheSize != null ? memoryCacheSize
                .equals(cacheSize.memoryCacheSize) : cacheSize.memoryCacheSize == null);
    }

    @Override
    public int hashCode() {
        int result = diskCacheSize != null ? diskCacheSize.hashCode() : 0;
        result = 31 * result + (memoryCacheSize != null ? memoryCacheSize.hashCode() : 0);
        return result;
    }
}
