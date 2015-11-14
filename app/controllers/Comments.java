package controllers;

/**
 *
 * @author Sara Rodriguez Soto
 */

import play.*;
import play.mvc.*;

@Check("admin")
@With(Secure.class)
public class Comments extends CRUD {    
}