package tom.ztpai;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/books")
public class BookController {
    
    private static final Map<Integer, Map<String, String>> books = new HashMap<>() {{
        put(1, Map.of("id", "1", "title", "Romeo i Julia", "author", "William Shakespear"));
        put(2, Map.of("id", "2", "title", "Odyseja", "author", "Homer"));
        put(3, Map.of("id", "3", "title", "Metro 2033", "author", "Dmitry Glukhovsky"));
        put(4, Map.of("id", "4", "title", "Uzumaki", "author", "Junji Ito"));
    }};
    
    
    @GetMapping
    public ResponseEntity<Object> getBooks() {
        return ResponseEntity.ok(books.values());
    }
    
    @GetMapping("/{idStr}")
    public ResponseEntity<Object> getBookById(@PathVariable String idStr) {
        if(!idStr.matches("\\d+"))
            return ResponseEntity.status(400).body(Map.of("Error", "ID must be a number"));
        
        int id = Integer.parseInt(idStr);
        if(!books.containsKey(id))
            return ResponseEntity.status(404).body(Map.of("Error", "Book not found"));
        
        return ResponseEntity.ok(books.get(id));
    }
}