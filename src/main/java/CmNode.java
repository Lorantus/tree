public class CmNode implements Comparable<CmNode> {
    private final String id;
    private final String name;

    public CmNode(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(CmNode o) {
        return id.compareTo(o.id);
    }
}
