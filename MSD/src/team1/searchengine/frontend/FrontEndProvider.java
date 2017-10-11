package team1.searchengine.frontend;

import team1.searchengine.model.*;
import java.util.*;

/**
 * Provides front-end functionality to analyze and store a data source so that
 * it can be queried upon through the Query Engine
 * 
 * @author hrivks
 *
 */
@FunctionalInterface
public interface FrontEndProvider {
	public PublicationEntityCollection convert(RawRecordCollection records, String source);
}
