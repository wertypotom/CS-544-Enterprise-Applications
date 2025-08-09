package cs544.partD;

import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;

import java.util.List;

@SpringBootApplication
public class PartDApplication implements CommandLineRunner {

	@Value("classpath:/docs/rental_housing_info_list.pdf")
	private Resource pdf;

	@Autowired
	private VectorStore vectorStore;

	public static void main(String[] args) {
		SpringApplication.run(PartDApplication.class, args);
	}

	@Override
	public void run(String... args) {
		var reader = new TikaDocumentReader(pdf);
		var splitter = new TokenTextSplitter();
		List<Document> chunks = splitter.apply(reader.get());
		chunks.forEach(d -> {
			d.getMetadata().putIfAbsent("source", pdf.getFilename());
		});

		vectorStore.add(chunks);
		System.out.println("Indexed " + chunks.size() + " PDF chunks into Redis.");
	}
}