package online.monstercorp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import online.monstercorp.model.BaseComponent;
import online.monstercorp.model.Node;
import online.monstercorp.model.SIDE;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestModels {


    public Node<BaseComponent> createTemplate(){
        BaseComponent.BaseFactory factory = new BaseComponent.BaseFactory();

        Node<BaseComponent> head  = factory
                .create(BaseComponent.BaseFactory.BASE_TYPE.TEMPLATE,"HEAD")
                .createNode(SIDE.NONE);

        Node<BaseComponent> firstSection = factory
                .create(BaseComponent.BaseFactory.BASE_TYPE.TEMPLATE,"Section one")
                .createNode();

        head.addChild(firstSection);

        Node<BaseComponent> componentNode = factory
                .create(BaseComponent.BaseFactory.BASE_TYPE.COMPONENT,"Left Element")
                .createNode(SIDE.LEFT);

        Node<BaseComponent> componentNodeSecond = factory
                .create(BaseComponent.BaseFactory.BASE_TYPE.COMPONENT,"Right Element")
                .createNode(SIDE.RIGHT);

        firstSection.addChild(componentNode);
        firstSection.addChild(componentNodeSecond);

        Node<BaseComponent> secondSection = factory
                .create(BaseComponent.BaseFactory.BASE_TYPE.TEMPLATE,"Section TWO")
                .createNode();
        head.addChild(secondSection);

        Node<BaseComponent> subSectionOne = factory
                .create(BaseComponent.BaseFactory.BASE_TYPE.TEMPLATE,"Sub Section One")
                .createNode();
        secondSection.addChild(subSectionOne);

        Node<BaseComponent> componentRightSubNode = factory
                .create(BaseComponent.BaseFactory.BASE_TYPE.COMPONENT,"Right Element")
                .createNode(SIDE.LEFT);

        Node<BaseComponent> componentLeftSubNode = factory
                .create(BaseComponent.BaseFactory.BASE_TYPE.COMPONENT,"Right Element")
                .createNode(SIDE.RIGHT);

        subSectionOne
                .addChild(componentLeftSubNode)
                .addChild(componentRightSubNode);

        return head;
    }


    @Test
    public void testModels_createTemplateWithSections_PrintOut() {

        Node<BaseComponent> head = createTemplate();
        String message = "";
        Node.printTree(head,message);

        System.out.println(message);

    }

    @Test
    public void writeToJson_writeToJsonAndToObject_resultIsNode(){
        Node<BaseComponent> head = createTemplate();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String json = gson.toJson(head);
        System.out.println(json);

        Node<BaseComponent> node = gson.fromJson(json, Node.class);
        for (Node<BaseComponent> child : node.getChildren()) {
            child.setParent(node);

        }

        node.setParents();
        // TODO need to setup type for Gson convertor for the class Generic
       // assertEquals(head,node);
    }


}
