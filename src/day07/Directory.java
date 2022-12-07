package day07;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Directory extends FileSystemEntry {

    private final Map<String, FileSystemEntry> children;

    protected Directory(String name) {
        super(name);
        children = new HashMap<>();
    }

    @Override
    public long getSize() {
        return children.values().stream().map(FileSystemEntry::getSize).mapToLong(Long::longValue).sum();
    }

    public Map<String, FileSystemEntry> getChildren() {
        return children;
    }

    public List<Directory> getSubDirectories() {
        return children.values().stream().filter(Directory.class::isInstance).map(Directory.class::cast).collect(Collectors.toList());
    }
}
