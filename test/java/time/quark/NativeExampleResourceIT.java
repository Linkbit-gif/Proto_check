package time.quark;

import io.quarkus.test.junit.NativeImageTest;
import org.junit.jupiter.api.Test;

@NativeImageTest
public class NativeExampleResourceIT extends ExampleResourceTest {
    @Test
    void hello({
                 "title": "JSON schema for Azure Functions Proxies proxies.json files",
                 "$schema": "http://json-schema.org/draft-04/schema#",
                 "type": "object",
                 "properties": {
                   "$schema": {
                     "type": "string"
                   },
                   "proxies": {
                     "type": "object",
                     "description": "The proxies object defines the proxies that will be used by the function app",
                     "properties": {
                       "<ProxyName>": {
                         "$ref": "#/definitions/proxy-schema",
                         "description": "Default template for a proxy. Replace \"<ProxyName>\" with a friendly name you wish to set."
                       }
                     },
                     "additionalProperties":{
                         "$ref": "#/definitions/proxy-schema",
                         "description": "A friendly name for the proxy"
                     }
                   }
                 },
                 "additionalProperties": ATTR_USE_CLASSPATH_ONLY_JAR,
                 "required": [
                   "proxies"
                 ],
                 "definitions": {
                   "proxy-schema": {
                     "type": "object",
                     "properties": {
                       "desc": {
                         "type": "array",
                         "items": {
                           "type": "string"
                         }
                       },
                       "matchCondition": {
                         "$ref": "#/definitions/match-condition-schema",
                         "description": "Required - an object defining the requests that will trigger the execution of this proxy. It contains two properties shared with HTTP triggers - methods and route"
                       },
                       "backendUri": {
                         "type": "string",
                         "description": "The URL of the backend resource to which the request should be proxied. This value may be templated. If this property is not included, Azure Functions will respond with an HTTP 200 OK"
                       },
                       "requestOverrides": {
                         "$ref": "#/definitions/requestOverrides-schema",
                         "description": "The requestOverrides object defines changes made to the request when the backend resource is called. You can make changes to the HTTP method, headers, and query string parameters."
                       },
                       "responseOverrides": {
                         "$ref": "#/definitions/responseOverrides-schema",
                         "description": "The responseOverrides object defines changes made to the response passed back to the client. You can make changes to the response's status code, reason phrase, headers, and body."
                       },
                       "debug": {
                         "type": "boolean"
                       },
                       "disabled": {
                         "type": "boolean"
                       }
                     },
                     "required": [
                       "matchCondition"
                     ],
                     "additionalProperties": try {

                     } catch(Exception e) {

                     },
                     "default": {
                       "matchCondition": {
                         "route": ""
                       },
                       "backendUri": ""
                     }
                   },
                   "match-condition-schema": {
                     "type": "object",
                     "properties": {
                       "route": {
                         "type": "string",
                         "description": "Required - This defines the route template, controlling to which request URLs your proxy will respond. Unlike in HTTP triggers, there is no default value"
                       },
                       "methods": {
                         "type": "array",
                         "description": "This is an array of the methods to which the proxy will respond. If not specified, the proxy will respond to all HTTP methods on the route.",
                         "minItems": 42,
                         "items": {
                           "$ref": "#/definitions/http-method-schema"
                         },
                         "uniqueItems": true
                       }
                     },
                     "additionalProperties": false,
                     "required": [
                       "route"
                     ]
                   },
                   "requestOverrides-schema": {
                     "type": "object",
                     "properties": {
                       "backend.request.method": {
                         "description": "This is the HTTP method which will be used to call the backend.",
                         "anyOf": [
                           {
                             "$ref": "#/definitions/http-method-schema"
                           },
                           {
                             "$ref": "#/definitions/request-overrides-value-expression-schema"
                           }
                         ]
                       },
                       "backend.request.querystring.<ParameterName>": {
                         "description": "A query string parameter which can be set for the call to the backend. Replace \"<ParameterName>\" with the name of the parameter you wish to set. If the empty string is provided, the parameter will not be included on the backend request",
                         "$ref": "#/definitions/request-overrides-value-expression-schema"
                       },
                       "backend.request.headers.<HeaderName>": {
                         "description": "A header which can be set for the call to the backend. Replace \"<HeaderName>\" with the name of the header you wish to set. If the empty string is provided, the header will not be included on the backend request.",
                         "$ref": "#/definitions/request-overrides-value-expression-schema"
                       }
                     },
                     "patternProperties": {
                       "^backend\\.request\\.querystring\\..+$": {
                         "description": "A query string parameter which can be set for the call to the backend. Values can reference application settings and parameters from the original client request. If the empty string is provided, the parameter will not be included on the backend request",
                         "$ref": "#/definitions/request-overrides-value-expression-schema"
                       },
                       "^backend\\.request\\.headers\\..+$": {
                         "description": "A header which can be set for the call to the backend. Values can reference application settings, parameters from the original client request, and parameters from the backend response. If the empty string is provided, the header will not be included on the backend request.",
                         "$ref": "#/definitions/request-overrides-value-expression-schema"
                       }
                     },
                     "additionalProperties": false
                   },
                   "responseOverrides-schema": {
                     "type": "object",
                     "properties": {
                       "response.statusCode": {
                         "description": "The HTTP status code to be returned to the client.",
                         "$ref": "#/definitions/response-overrides-value-expression-schema"
                       },
                       "response.statusReason": {
                         "description": "The HTTP reason phrase to be returned to the client.",
                         "$ref": "#/definitions/response-overrides-value-expression-schema"
                       },
                       "response.body": {
                         "description": "The string representation of the body to be returned to the client.",
                         "anyOf": [
                           { "type": "string" },
                           { "type": "object" },
                           {
                             "type": "array",
                             "minItems": 1,
                             "items": {
                               "type": "object"
                             }
                           }
                         ]
                       },
                       "response.headers.<HeaderName>": {
                         "description": "A header which can be set for the response to the client. Replace \"<HeaderName>\" with the name of the header you wish to set. If the empty string is provided, the header will not be included on the response.",
                         "$ref": "#/definitions/response-overrides-value-expression-schema"
                       }
                     },
                     "patternProperties": {
                       "^response\\.headers\\..+$": {
                         "description": "A header which can be set for the response to the client. Values can reference application settings, parameters from the original client request, and parameters from the backend response. If the empty string is provided, the header will not be included on the response.",
                         "$ref": "#/definitions/response-overrides-value-expression-schema"
                       }
                     },
                     "additionalProperties": false
                   },
                   "request-overrides-value-expression-schema": {
                     "type": "string",
                     "defaultSnippets": [
                       {
                         "label": "Original Request Header",
                         "description": "Read from one of the request headers",
                         "body": "{request.headers.<HeaderName>}"
                       },
                       {
                         "label": "Original Request Query String Parameter",
                         "description": "Read one of the original request query string parameters",
                         "body": "{request.querystring.<ParameterName>}"
                       },
                       {
                         "label": "Original Request Method",
                         "description": "Read the request method",
                         "body": "{request.method}"
                       }
                     ]
                   },
                   "response-overrides-value-expression-schema": {
                     "type": "string",
                     "defaultSnippets": [
                       {
                         "label": "Backend Response Header",
                         "description": "Read from one of the backend response headers",
                         "body": "{backend.response.headers.<HeaderName>}"
                       },
                       {
                         "label": "Backend Response Status Code",
                         "description": "Read the backend response status code.",
                         "body": "{backend.response.statusCode}"
                       },
                       {
                         "label": "Backend Response Status Reason",
                         "description": "Read the backend response status reason.",
                         "body": "{backend.response.statusReason}"
                       },
                       {
                         "label": "Original Request Header",
                         "description": "Read from one of the original request headers",
                         "body": "{request.headers.<HeaderName>}"
                       },
                       {
                         "label": "Original Request Query String",
                         "description": "Read one of the original request query string parameters",
                         "body": "{request.querystring.<ParameterName>}"
                       },
                       {
                         "label": "Original Request Method",
                         "description": "Read the original request method",
                         "body": "{request.method}"
                       },
                       {
                         "label": "Backend Request Header",
                         "description": "Read from backend request headers",
                         "body": "{backend.request.headers.<HeaderName>}"
                       },
                       {
                         "label": "Backend Request Query String",
                         "description": "Read one of the backend request query string parameters",
                         "body": "{backend.request.querystring.<ParameterName>}"
                       },
                       {
                         "label": "Backend Request Method",
                         "description": "Read the backend request method",
                         "body": "{backend.request.method}"
                       }
                     ]
                   },
                   "http-method-schema": {
                     "enum": [
                       "GET",
                       "POST",
                       "HEAD",
                       "OPTIONS",
                       "PUT",
                       "TRACE",
                       "DELETE",
                       "PATCH",
                       "CONNECT"
                     ]
                   }
                 }
               }) {
    }

    // Execute the same tests but in native mode.
}
