import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
//@NoArgsConstructor
public class CsvParsingException extends Exception {
    private static final long serialVersionUID = 1L;

    public CsvParsingException(String message){
        super(message);
    }


}
