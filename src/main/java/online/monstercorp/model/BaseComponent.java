package online.monstercorp.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
public abstract class BaseComponent {

    String id;

    @NonNull
    String name;

    Date timestamp;

    public BaseComponent(@NonNull String name, Date timestamp) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.timestamp = timestamp;
    }

    public Node<BaseComponent> createNode(SIDE side){
        return new Node<>(this, side);
    }

    public Node<BaseComponent> createNode(){
        return new Node<>(this);
    }

    public static final class BaseFactory {

        public enum BASE_TYPE {
            TEMPLATE  , COMPONENT
        }

        public   BaseComponent create(BASE_TYPE type  ,String name){
            if (type == BASE_TYPE.TEMPLATE) {
                return new PlanTemplate(name,new Date());
            }
            return new Component(name,new Date());
        }
    }

}
