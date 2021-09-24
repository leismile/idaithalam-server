package ch.inss.virtualan.idaiserver.security;

/** This packages ensures security via API Keys.
 *  The check is in this order:
 *  1. Check the general API key with bean from ApiKeyRequestFilter.
 *     The token is defined in the application.properties.
 *  2. Check the user API key with filter from UserFilter class.
 *  3. The userid from field X-USER-APIKEY must match the requested ressource (id in path).
 *     TODO.
 * 
 * */