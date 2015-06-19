import tml.vectorspace.TermWeighting.GlobalWeight;
import tml.vectorspace.TermWeighting.LocalWeight;
import tml.vectorspace.operations.ConceptExtraction;
import tml.vectorspace.operations.PassagesSimilarity;
import tml.annotators.PennTreeAnnotator;
import tml.corpus.SearchResultsCorpus;
import tml.corpus.CorpusParameters.DimensionalityReduction;
import tml.corpus.CorpusParameters.TermSelection;
import tml.storage.Repository;

public class PerformingOperationOnCorpus {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		Repository repository = new Repository("C:\\BerryDumais");
//		repository.addDocumentsInFolder("C:\\handbookOfLSA/");
        SearchResultsCorpus corpus = new SearchResultsCorpus("type:document");
        corpus.getParameters().setTermSelectionCriterion(TermSelection.DF);
        corpus.getParameters().setTermSelectionThreshold(0);
        corpus.getParameters().setDimensionalityReduction(DimensionalityReduction.NUM);
        corpus.getParameters().setDimensionalityReductionThreshold(100);
        corpus.getParameters().setTermWeightGlobal(GlobalWeight.Entropy);
        corpus.getParameters().setTermWeightLocal(LocalWeight.LOGTF);
        corpus.load(repository);
        
        System.out.println("Corpus loaded and Semantic space calculated");
        System.out.println("Total documents:" + corpus.getPassages().length);

        PassagesSimilarity distances = new PassagesSimilarity();
//        ConceptExtraction distances = new ConceptExtraction();
        
        distances.setCorpus(corpus);
        distances.start();
        
        distances.getSimilarities();
        distances.getResultsXML();

        distances.printResults();

		
	}

}
