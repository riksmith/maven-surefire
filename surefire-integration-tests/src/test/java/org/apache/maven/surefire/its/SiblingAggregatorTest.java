package org.apache.maven.surefire.its;


import junit.framework.TestCase;
import org.apache.maven.it.Verifier;
import org.apache.maven.it.util.ResourceExtractor;

import java.io.File;

/**
 * Test aggregator as a sibling to child modules; invokes modules as "../child"
 * 
 * @author <a href="mailto:dfabulich@apache.org">Dan Fabulich</a>
 * 
 */
public class SiblingAggregatorTest
    extends TestCase
{
    public void testSiblingAggregator ()
        throws Exception
    {
        File testDir = ResourceExtractor.simpleExtractResources( getClass(), "/sibling-aggregator" );

        File aggregatorDir = new File( testDir, "aggregator" );
        
        Verifier verifier = new Verifier( aggregatorDir.getAbsolutePath() );
        verifier.executeGoal( "test" );
        verifier.verifyErrorFreeLog();
        verifier.resetStreams();
        
        File child2Dir = new File( testDir, "child2" );
        
        HelperAssertions.assertTestSuiteResults( 1, 0, 0, 0, child2Dir );        
    }
}