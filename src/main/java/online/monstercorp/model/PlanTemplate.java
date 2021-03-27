package online.monstercorp.model;

import lombok.*;

import java.util.Date;


@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class PlanTemplate extends BaseComponent {

    public PlanTemplate( @NonNull String name, Date timestamp) {
        super( name, timestamp);
    }

    @Override
    public String toString() {
        return "PlanTemplate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
