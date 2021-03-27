package online.monstercorp.model;

import lombok.*;


import java.util.Date;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class Component extends BaseComponent {

   private Map<String,String> propValues;

   public Component( @NonNull String name, Date timestamp) {
      super( name, timestamp);
   }

   public Component( @NonNull String name, Date timestamp, Map<String, String> propValues) {
      super( name, timestamp);
      this.propValues = propValues;
   }

   @Override
   public String toString() {
      return "Component{" +
              "id=" + id +
              ", name='" + name + '\'' +
              ", timestamp=" + timestamp +
              ", propValues=" + propValues +
              '}';
   }
}
