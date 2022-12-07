package day07;

public abstract class FileSystemEntry {

    private final String name;

    protected FileSystemEntry(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract long getSize();
}
