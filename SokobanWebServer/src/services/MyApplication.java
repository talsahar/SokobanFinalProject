package services;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * 
 * A web server which connected to the central server, provides
 * all the online services for Sokoban users. 
 * 
 * Sokoban Web Server based on a restful approach, written by using the jersey
 *  framework which provides a great web server API for developers.
 *  
 * The data exchanged between the central
 * server and the web server transferred as an XML-Object based on the Restful
 * HTTP @Get and @Post approach for scalability and stable web-server. 
 * 
 * It also stores the game data collection on Microsoft SQL Server 2016 in "SokobanDB"
 * database which includes various schemes and responsible for exchanging data
 * with it effectively thanks the hibernate ORM, and accessed by the JDBC API.
 * 
 * The web-server provides 4 different services: 
 * 1. Records Exchanging Service: Exchanging records with the dbo.Record table 
 * supports save, load by level-name or player-name. 
 * 2. Solver Service: Using the "Sokoban Solver" jar above to solve and return a
 * solution of Sokoban level. 
 * 3. Solution Cashes Service: Stores solutions on database by its level hash, so 
 * that in the next requests on the same level it could just extract the previous 
 * solution from database without solving it again. 
 * 4. Main Solver Service: Using the two Services above to return the requested 
 * level solution as fast as possible.
 *
 * 
 */
@ApplicationPath("/")
public class MyApplication extends ResourceConfig {
	public MyApplication() {
		super(SolveService.class, SolutionCache.class, Solver.class, RecordService.class);

	}
}
