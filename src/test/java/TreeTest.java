import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;

public class TreeTest {

    @Test
    public void test() {
        Tree<CmNode> cmNodeTree = new Tree<>(
                () -> new TreeSet<>(Comparator.comparing(Tree.Node::getData)));

        cmNodeTree.getRoot().setData(new CmNode("id1", "Name 1"));
        cmNodeTree.getRoot().createNode(new CmNode("id2", "name 2"));
        cmNodeTree.getRoot().createNode(new CmNode("id5", "name 3"));
        cmNodeTree.getRoot().createNode(new CmNode("id3", "name 5"));
        cmNodeTree.getRoot().createNode(new CmNode("id4", "name 4"));

        assertThat(cmNodeTree.getRoot().getChildren())
                .extracting(node -> node.getData().getId())
                .containsExactly(
                        "id2",
                        "id3",
                        "id4",
                        "id5");

        Tree.Node<CmNode> cmNodeNode = cmNodeTree.createNode();
        cmNodeNode.setData(new CmNode("id1", "name 1"));
        cmNodeNode.createNode(new CmNode("id2", "name 2"));
        cmNodeNode.createNode(new CmNode("id5", "name 3"));
        cmNodeNode.createNode(new CmNode("id3", "name 5"));
        cmNodeNode.createNode(new CmNode("id4", "name 4"));

        assertThat(cmNodeNode.getChildren())
                .extracting(node -> node.getData().getId())
                .containsExactly(
                        "id2",
                        "id3",
                        "id4",
                        "id5");

        Tree.Node<CmNode> cmNodeNodeReversed = cmNodeNode
                .withCollection(() -> new TreeSet<>(Comparator.comparing((Tree.Node<CmNode> node) -> node.getData().getName())));
        cmNodeNodeReversed.createNode(new CmNode("id2", "name 2"));
        cmNodeNodeReversed.createNode(new CmNode("id5", "name 3"));
        cmNodeNodeReversed.createNode(new CmNode("id3", "name 5"));
        cmNodeNodeReversed.createNode(new CmNode("id4", "name 4"));

        assertThat(cmNodeNodeReversed.getChildren())
                .extracting(node -> node.getData().getName())
                .containsExactly(
                        "name 2",
                        "name 3",
                        "name 4",
                        "name 5");

        Tree<CmNode> cmNodeTreeReversed = new Tree<>(
                () -> new TreeSet<>(Comparator.comparing((Tree.Node<CmNode> node) -> node.getData().getName()).reversed()));
        cmNodeTreeReversed.getRoot().setData(new CmNode("id1", "Name 1"));
        cmNodeTreeReversed.getRoot().createNode(new CmNode("id2", "name 2"));
        cmNodeTreeReversed.getRoot().createNode(new CmNode("id5", "name 3"));
        cmNodeTreeReversed.getRoot().createNode(new CmNode("id3", "name 5"));
        cmNodeTreeReversed.getRoot().createNode(new CmNode("id4", "name 4"));

        assertThat(cmNodeTreeReversed.getRoot().getChildren())
                .extracting(node -> node.getData().getName())
                .containsExactly(
                        "name 5",
                        "name 4",
                        "name 3",
                        "name 2");

        Tree<CmNode> cmNodeTreeList = new Tree<>(() -> new ArrayList<>());
        cmNodeTreeList.getRoot().setData(new CmNode("id1", "Name 1"));
        cmNodeTreeList.getRoot().createNode(new CmNode("id2", "name 2"));
        cmNodeTreeList.getRoot().createNode(new CmNode("id5", "name 3"));
        cmNodeTreeList.getRoot().createNode(new CmNode("id3", "name 5"));
        cmNodeTreeList.getRoot().createNode(new CmNode("id4", "name 4"));

        assertThat(cmNodeTreeList.getRoot().getChildren())
                .extracting(node -> node.getData().getId())
                .containsExactly(
                        "id2",
                        "id5",
                        "id3",
                        "id4");
    }
}
