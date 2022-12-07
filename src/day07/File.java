package day07;

public class File extends FileSystemEntry {

    private final long size;

    protected File(String name, long size) {
        super(name);
        this.size = size;
    }

    @Override
    public long getSize() {
        return size;
    }
}
