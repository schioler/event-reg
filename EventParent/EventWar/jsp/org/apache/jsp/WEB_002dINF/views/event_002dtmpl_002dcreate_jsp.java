/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: jetty/12.0.11
 * Generated at: 2024-11-30 14:26:55 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.jsp.*;

public final class event_002dtmpl_002dcreate_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports,
                 org.apache.jasper.runtime.JspSourceDirectives {

  private static final jakarta.servlet.jsp.JspFactory _jspxFactory =
          jakarta.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(5);
    _jspx_dependants.put("jar:file:/C:/Users/lars/.m2/repository/org/glassfish/web/jakarta.servlet.jsp.jstl/3.0.1/jakarta.servlet.jsp.jstl-3.0.1.jar!/META-INF/c-1_2.tld", Long.valueOf(1664446278000L));
    _jspx_dependants.put("/WEB-INF/views/include/status.jsp", Long.valueOf(1732868083430L));
    _jspx_dependants.put("/WEB-INF/views/include/menu.jsp", Long.valueOf(1732701823923L));
    _jspx_dependants.put("jar:file:/C:/Users/lars/.m2/repository/org/glassfish/web/jakarta.servlet.jsp.jstl/3.0.1/jakarta.servlet.jsp.jstl-3.0.1.jar!/META-INF/fmt-1_1.tld", Long.valueOf(1664446278000L));
    _jspx_dependants.put("file:/C:/Users/lars/.m2/repository/org/glassfish/web/jakarta.servlet.jsp.jstl/3.0.1/jakarta.servlet.jsp.jstl-3.0.1.jar", Long.valueOf(1720964835183L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.LinkedHashSet<>(4);
    _jspx_imports_packages.add("jakarta.servlet");
    _jspx_imports_packages.add("jakarta.servlet.http");
    _jspx_imports_packages.add("jakarta.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005ffmt_005fbundle_0026_005fbasename;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems;

  private volatile jakarta.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public boolean getErrorOnELNotFound() {
    return false;
  }

  public jakarta.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005ffmt_005fbundle_0026_005fbasename = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005ffmt_005fbundle_0026_005fbasename.release();
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.release();
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.release();
  }

  public void _jspService(final jakarta.servlet.http.HttpServletRequest request, final jakarta.servlet.http.HttpServletResponse response)
      throws java.io.IOException, jakarta.servlet.ServletException {

    if (!jakarta.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
        return;
      }
    }

    final jakarta.servlet.jsp.PageContext pageContext;
    jakarta.servlet.http.HttpSession session = null;
    final jakarta.servlet.ServletContext application;
    final jakarta.servlet.ServletConfig config;
    jakarta.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    jakarta.servlet.jsp.JspWriter _jspx_out = null;
    jakarta.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<!DOCTYPE html>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("	function changeValue(element, value) {\r\n");
      out.write("		document.getElementById(element).value = value;\r\n");
      out.write("	}\r\n");
      out.write("</script>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("\r\n");
      out.write("<title>EventTemplate</title>\r\n");
      out.write("<link rel=\"stylesheet\" href=\"public/styles/cols.css\">\r\n");
      out.write("<link rel=\"stylesheet\" href=\"public/styles/event.css\">\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("	");
      if (_jspx_meth_fmt_005fbundle_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof jakarta.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_fmt_005fbundle_005f0(jakarta.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    jakarta.servlet.jsp.PageContext pageContext = _jspx_page_context;
    jakarta.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  fmt:bundle
    org.apache.taglibs.standard.tag.rt.fmt.BundleTag _jspx_th_fmt_005fbundle_005f0 = (org.apache.taglibs.standard.tag.rt.fmt.BundleTag) _005fjspx_005ftagPool_005ffmt_005fbundle_0026_005fbasename.get(org.apache.taglibs.standard.tag.rt.fmt.BundleTag.class);
    boolean _jspx_th_fmt_005fbundle_005f0_reused = false;
    try {
      _jspx_th_fmt_005fbundle_005f0.setPageContext(_jspx_page_context);
      _jspx_th_fmt_005fbundle_005f0.setParent(null);
      // /WEB-INF/views/event-tmpl-create.jsp(20,1) name = basename type = null reqTime = true required = true fragment = false deferredValue = false deferredMethod = false expectedTypeName = null methodSignature = null 
      _jspx_th_fmt_005fbundle_005f0.setBasename("dk.schioler.event.base.resources.EventListResources");
      int _jspx_eval_fmt_005fbundle_005f0 = _jspx_th_fmt_005fbundle_005f0.doStartTag();
      if (_jspx_eval_fmt_005fbundle_005f0 != jakarta.servlet.jsp.tagext.Tag.SKIP_BODY) {
        if (_jspx_eval_fmt_005fbundle_005f0 != jakarta.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = org.apache.jasper.runtime.JspRuntimeLibrary.startBufferedBody(_jspx_page_context, _jspx_th_fmt_005fbundle_005f0);
        }
        do {
          out.write("\r\n");
          out.write("\r\n");
          out.write("		");
          out.write("\r\n");
          out.write("<div>\r\n");
          out.write("	");
          if (_jspx_meth_fmt_005fbundle_005f1(_jspx_th_fmt_005fbundle_005f0, _jspx_page_context))
            return true;
          out.write("\r\n");
          out.write("</div>\r\n");
          out.write("\r\n");
          out.write("\r\n");
          out.write("		");
          out.write("\r\n");
          out.write("<div>\r\n");
          out.write("   <hr>\r\n");
          out.write("   <p>Status:</p>\r\n");
          out.write("	");
          if (_jspx_meth_c_005fforEach_005f0(_jspx_th_fmt_005fbundle_005f0, _jspx_page_context))
            return true;
          out.write("\r\n");
          out.write("	<hr>\r\n");
          out.write("</div>\r\n");
          out.write("\r\n");
          out.write("\r\n");
          out.write("\r\n");
          out.write("		<h3>\r\n");
          out.write("			");
          if (_jspx_meth_fmt_005fmessage_005f7(_jspx_th_fmt_005fbundle_005f0, _jspx_page_context))
            return true;
          out.write("\r\n");
          out.write("		</h3>\r\n");
          out.write("		<hr color=\"blue\">\r\n");
          out.write("\r\n");
          out.write("		<form method=\"post\" action=\"./event-tmpl-create.do\">\r\n");
          out.write("			<div class=\"container-two-col\">\r\n");
          out.write("				<div>\r\n");
          out.write("					<span> Name</span>\r\n");
          out.write("				</div>\r\n");
          out.write("				<div>\r\n");
          out.write("					<input type=\"hidden\" name=\"event-type-id\" 	value=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${sesEventTemplate.parentId}", java.lang.String.class, (jakarta.servlet.jsp.PageContext)_jspx_page_context, null));
          out.write("\"> \r\n");
          out.write("					<input type=\"hidden\" name=\"login-id\"  value=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${sesEventTemplate.loginId}", java.lang.String.class, (jakarta.servlet.jsp.PageContext)_jspx_page_context, null));
          out.write("\">\r\n");
          out.write("					<input type=\"text\" 	id=\"name\" name=\"name\" value=\"enter name\" onclick=\"changeValue('name', '');\">\r\n");
          out.write("				</div>\r\n");
          out.write("				<div>\r\n");
          out.write("					<span>short Name</span>\r\n");
          out.write("				</div>\r\n");
          out.write("				<div>\r\n");
          out.write("					<input type=\"text\" id=\"short-name\" name=\"short-name\"\r\n");
          out.write("						value=\"enter short name\" onclick=\"changeValue('short-name', '');\">\r\n");
          out.write("				</div>\r\n");
          out.write("				<div>Dose</div>\r\n");
          out.write("				<div>\r\n");
          out.write("					<input type=\"text\" name=\"dose\" id=\"dose\" value=\"enter dose\"\r\n");
          out.write("						onclick=\"changeValue('dose', '');\">\r\n");
          out.write("				</div>\r\n");
          out.write("				<div>Unit</div>\r\n");
          out.write("				<div>\r\n");
          out.write("					<input type=\"text\" id=\"unit\" name=\"unit\" value=\"enter unit\"\r\n");
          out.write("						onclick=\"changeValue('unit', '');\">\r\n");
          out.write("				</div>\r\n");
          out.write("				<div>Description</div>\r\n");
          out.write("				<div>\r\n");
          out.write("					<input type=\"text\" id=\"description\" name=\"description\"\r\n");
          out.write("						value=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${sesEventTemplate.description }", java.lang.String.class, (jakarta.servlet.jsp.PageContext)_jspx_page_context, null));
          out.write("\"\r\n");
          out.write("						onclick=\"changeValue('description', '');\">\r\n");
          out.write("				</div>\r\n");
          out.write("				<div>&nbsp;</div>\r\n");
          out.write("				<div>\r\n");
          out.write("				          \r\n");
          out.write("					<input  class=\"tmpl-text-btn\" type=\"submit\" value=\"Create\">\r\n");
          out.write("				</div>\r\n");
          out.write("			</div>\r\n");
          out.write("		</form>\r\n");
          out.write("\r\n");
          out.write("\r\n");
          out.write("	");
          int evalDoAfterBody = _jspx_th_fmt_005fbundle_005f0.doAfterBody();
          if (evalDoAfterBody != jakarta.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
        if (_jspx_eval_fmt_005fbundle_005f0 != jakarta.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.popBody();
        }
      }
      if (_jspx_th_fmt_005fbundle_005f0.doEndTag() == jakarta.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
      _005fjspx_005ftagPool_005ffmt_005fbundle_0026_005fbasename.reuse(_jspx_th_fmt_005fbundle_005f0);
      _jspx_th_fmt_005fbundle_005f0_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_fmt_005fbundle_005f0, _jsp_getInstanceManager(), _jspx_th_fmt_005fbundle_005f0_reused);
    }
    return false;
  }

  private boolean _jspx_meth_fmt_005fbundle_005f1(jakarta.servlet.jsp.tagext.JspTag _jspx_th_fmt_005fbundle_005f0, jakarta.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    jakarta.servlet.jsp.PageContext pageContext = _jspx_page_context;
    jakarta.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  fmt:bundle
    org.apache.taglibs.standard.tag.rt.fmt.BundleTag _jspx_th_fmt_005fbundle_005f1 = (org.apache.taglibs.standard.tag.rt.fmt.BundleTag) _005fjspx_005ftagPool_005ffmt_005fbundle_0026_005fbasename.get(org.apache.taglibs.standard.tag.rt.fmt.BundleTag.class);
    boolean _jspx_th_fmt_005fbundle_005f1_reused = false;
    try {
      _jspx_th_fmt_005fbundle_005f1.setPageContext(_jspx_page_context);
      _jspx_th_fmt_005fbundle_005f1.setParent((jakarta.servlet.jsp.tagext.Tag) _jspx_th_fmt_005fbundle_005f0);
      // /WEB-INF/views/include/menu.jsp(3,1) name = basename type = null reqTime = true required = true fragment = false deferredValue = false deferredMethod = false expectedTypeName = null methodSignature = null 
      _jspx_th_fmt_005fbundle_005f1.setBasename("dk.schioler.event.base.resources.EventListResources");
      int _jspx_eval_fmt_005fbundle_005f1 = _jspx_th_fmt_005fbundle_005f1.doStartTag();
      if (_jspx_eval_fmt_005fbundle_005f1 != jakarta.servlet.jsp.tagext.Tag.SKIP_BODY) {
        if (_jspx_eval_fmt_005fbundle_005f1 != jakarta.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = org.apache.jasper.runtime.JspRuntimeLibrary.startBufferedBody(_jspx_page_context, _jspx_th_fmt_005fbundle_005f1);
        }
        do {
          out.write("\r\n");
          out.write("		<ul>\r\n");
          out.write("			<li>\r\n");
          out.write("			   <a href=\"event-type-list-show.do\"> \r\n");
          out.write("			      ");
          if (_jspx_meth_fmt_005fmessage_005f0(_jspx_th_fmt_005fbundle_005f1, _jspx_page_context))
            return true;
          out.write("\r\n");
          out.write("			   </a>\r\n");
          out.write("			</li>\r\n");
          out.write("			<li>\r\n");
          out.write("			   <a href=\"event-tmpl-list-show.do  \">\r\n");
          out.write("			      ");
          if (_jspx_meth_fmt_005fmessage_005f1(_jspx_th_fmt_005fbundle_005f1, _jspx_page_context))
            return true;
          out.write("\r\n");
          out.write("			   </a>\r\n");
          out.write("			</li>\r\n");
          out.write("			<li>\r\n");
          out.write("			   <a href=\"search-new-show.do\"> \r\n");
          out.write("			      ");
          if (_jspx_meth_fmt_005fmessage_005f2(_jspx_th_fmt_005fbundle_005f1, _jspx_page_context))
            return true;
          out.write("\r\n");
          out.write("			   </a>\r\n");
          out.write("			</li>\r\n");
          out.write("			<li>\r\n");
          out.write("			   <a href=\"favorites-show.do\"> \r\n");
          out.write("			      ");
          if (_jspx_meth_fmt_005fmessage_005f3(_jspx_th_fmt_005fbundle_005f1, _jspx_page_context))
            return true;
          out.write("\r\n");
          out.write("			   </a>\r\n");
          out.write("			</li>\r\n");
          out.write("			<li>\r\n");
          out.write("			   <a href=\"users-show.do\">\r\n");
          out.write("			      ");
          if (_jspx_meth_fmt_005fmessage_005f4(_jspx_th_fmt_005fbundle_005f1, _jspx_page_context))
            return true;
          out.write("\r\n");
          out.write("		      </a>\r\n");
          out.write("		   </li>\r\n");
          out.write("\r\n");
          out.write("			<li>\r\n");
          out.write("			   <a href=\"#contact\">\r\n");
          out.write("			      ");
          if (_jspx_meth_fmt_005fmessage_005f5(_jspx_th_fmt_005fbundle_005f1, _jspx_page_context))
            return true;
          out.write("\r\n");
          out.write("			   </a>\r\n");
          out.write("			</li>\r\n");
          out.write("			<li style=\"float: right\">\r\n");
          out.write("			   <a href=\"./logout.do\">\r\n");
          out.write("			      ");
          if (_jspx_meth_fmt_005fmessage_005f6(_jspx_th_fmt_005fbundle_005f1, _jspx_page_context))
            return true;
          out.write("\r\n");
          out.write("			   </a>\r\n");
          out.write("			 </li>\r\n");
          out.write("\r\n");
          out.write("\r\n");
          out.write("		</ul>\r\n");
          out.write("\r\n");
          out.write("	");
          int evalDoAfterBody = _jspx_th_fmt_005fbundle_005f1.doAfterBody();
          if (evalDoAfterBody != jakarta.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
        if (_jspx_eval_fmt_005fbundle_005f1 != jakarta.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.popBody();
        }
      }
      if (_jspx_th_fmt_005fbundle_005f1.doEndTag() == jakarta.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
      _005fjspx_005ftagPool_005ffmt_005fbundle_0026_005fbasename.reuse(_jspx_th_fmt_005fbundle_005f1);
      _jspx_th_fmt_005fbundle_005f1_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_fmt_005fbundle_005f1, _jsp_getInstanceManager(), _jspx_th_fmt_005fbundle_005f1_reused);
    }
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f0(jakarta.servlet.jsp.tagext.JspTag _jspx_th_fmt_005fbundle_005f1, jakarta.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    jakarta.servlet.jsp.PageContext pageContext = _jspx_page_context;
    //  fmt:message
    org.apache.taglibs.standard.tag.rt.fmt.MessageTag _jspx_th_fmt_005fmessage_005f0 = (org.apache.taglibs.standard.tag.rt.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.rt.fmt.MessageTag.class);
    boolean _jspx_th_fmt_005fmessage_005f0_reused = false;
    try {
      _jspx_th_fmt_005fmessage_005f0.setPageContext(_jspx_page_context);
      _jspx_th_fmt_005fmessage_005f0.setParent((jakarta.servlet.jsp.tagext.Tag) _jspx_th_fmt_005fbundle_005f1);
      // /WEB-INF/views/include/menu.jsp(8,9) name = key type = null reqTime = true required = false fragment = false deferredValue = false deferredMethod = false expectedTypeName = null methodSignature = null 
      _jspx_th_fmt_005fmessage_005f0.setKey("event-type.menu");
      int _jspx_eval_fmt_005fmessage_005f0 = _jspx_th_fmt_005fmessage_005f0.doStartTag();
      if (_jspx_th_fmt_005fmessage_005f0.doEndTag() == jakarta.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f0);
      _jspx_th_fmt_005fmessage_005f0_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_fmt_005fmessage_005f0, _jsp_getInstanceManager(), _jspx_th_fmt_005fmessage_005f0_reused);
    }
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f1(jakarta.servlet.jsp.tagext.JspTag _jspx_th_fmt_005fbundle_005f1, jakarta.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    jakarta.servlet.jsp.PageContext pageContext = _jspx_page_context;
    //  fmt:message
    org.apache.taglibs.standard.tag.rt.fmt.MessageTag _jspx_th_fmt_005fmessage_005f1 = (org.apache.taglibs.standard.tag.rt.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.rt.fmt.MessageTag.class);
    boolean _jspx_th_fmt_005fmessage_005f1_reused = false;
    try {
      _jspx_th_fmt_005fmessage_005f1.setPageContext(_jspx_page_context);
      _jspx_th_fmt_005fmessage_005f1.setParent((jakarta.servlet.jsp.tagext.Tag) _jspx_th_fmt_005fbundle_005f1);
      // /WEB-INF/views/include/menu.jsp(13,9) name = key type = null reqTime = true required = false fragment = false deferredValue = false deferredMethod = false expectedTypeName = null methodSignature = null 
      _jspx_th_fmt_005fmessage_005f1.setKey("event-template.menu");
      int _jspx_eval_fmt_005fmessage_005f1 = _jspx_th_fmt_005fmessage_005f1.doStartTag();
      if (_jspx_th_fmt_005fmessage_005f1.doEndTag() == jakarta.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f1);
      _jspx_th_fmt_005fmessage_005f1_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_fmt_005fmessage_005f1, _jsp_getInstanceManager(), _jspx_th_fmt_005fmessage_005f1_reused);
    }
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f2(jakarta.servlet.jsp.tagext.JspTag _jspx_th_fmt_005fbundle_005f1, jakarta.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    jakarta.servlet.jsp.PageContext pageContext = _jspx_page_context;
    //  fmt:message
    org.apache.taglibs.standard.tag.rt.fmt.MessageTag _jspx_th_fmt_005fmessage_005f2 = (org.apache.taglibs.standard.tag.rt.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.rt.fmt.MessageTag.class);
    boolean _jspx_th_fmt_005fmessage_005f2_reused = false;
    try {
      _jspx_th_fmt_005fmessage_005f2.setPageContext(_jspx_page_context);
      _jspx_th_fmt_005fmessage_005f2.setParent((jakarta.servlet.jsp.tagext.Tag) _jspx_th_fmt_005fbundle_005f1);
      // /WEB-INF/views/include/menu.jsp(18,9) name = key type = null reqTime = true required = false fragment = false deferredValue = false deferredMethod = false expectedTypeName = null methodSignature = null 
      _jspx_th_fmt_005fmessage_005f2.setKey("search.menu");
      int _jspx_eval_fmt_005fmessage_005f2 = _jspx_th_fmt_005fmessage_005f2.doStartTag();
      if (_jspx_th_fmt_005fmessage_005f2.doEndTag() == jakarta.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f2);
      _jspx_th_fmt_005fmessage_005f2_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_fmt_005fmessage_005f2, _jsp_getInstanceManager(), _jspx_th_fmt_005fmessage_005f2_reused);
    }
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f3(jakarta.servlet.jsp.tagext.JspTag _jspx_th_fmt_005fbundle_005f1, jakarta.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    jakarta.servlet.jsp.PageContext pageContext = _jspx_page_context;
    //  fmt:message
    org.apache.taglibs.standard.tag.rt.fmt.MessageTag _jspx_th_fmt_005fmessage_005f3 = (org.apache.taglibs.standard.tag.rt.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.rt.fmt.MessageTag.class);
    boolean _jspx_th_fmt_005fmessage_005f3_reused = false;
    try {
      _jspx_th_fmt_005fmessage_005f3.setPageContext(_jspx_page_context);
      _jspx_th_fmt_005fmessage_005f3.setParent((jakarta.servlet.jsp.tagext.Tag) _jspx_th_fmt_005fbundle_005f1);
      // /WEB-INF/views/include/menu.jsp(23,9) name = key type = null reqTime = true required = false fragment = false deferredValue = false deferredMethod = false expectedTypeName = null methodSignature = null 
      _jspx_th_fmt_005fmessage_005f3.setKey("favorites.title");
      int _jspx_eval_fmt_005fmessage_005f3 = _jspx_th_fmt_005fmessage_005f3.doStartTag();
      if (_jspx_th_fmt_005fmessage_005f3.doEndTag() == jakarta.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f3);
      _jspx_th_fmt_005fmessage_005f3_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_fmt_005fmessage_005f3, _jsp_getInstanceManager(), _jspx_th_fmt_005fmessage_005f3_reused);
    }
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f4(jakarta.servlet.jsp.tagext.JspTag _jspx_th_fmt_005fbundle_005f1, jakarta.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    jakarta.servlet.jsp.PageContext pageContext = _jspx_page_context;
    //  fmt:message
    org.apache.taglibs.standard.tag.rt.fmt.MessageTag _jspx_th_fmt_005fmessage_005f4 = (org.apache.taglibs.standard.tag.rt.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.rt.fmt.MessageTag.class);
    boolean _jspx_th_fmt_005fmessage_005f4_reused = false;
    try {
      _jspx_th_fmt_005fmessage_005f4.setPageContext(_jspx_page_context);
      _jspx_th_fmt_005fmessage_005f4.setParent((jakarta.servlet.jsp.tagext.Tag) _jspx_th_fmt_005fbundle_005f1);
      // /WEB-INF/views/include/menu.jsp(28,9) name = key type = null reqTime = true required = false fragment = false deferredValue = false deferredMethod = false expectedTypeName = null methodSignature = null 
      _jspx_th_fmt_005fmessage_005f4.setKey("user.title");
      int _jspx_eval_fmt_005fmessage_005f4 = _jspx_th_fmt_005fmessage_005f4.doStartTag();
      if (_jspx_th_fmt_005fmessage_005f4.doEndTag() == jakarta.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f4);
      _jspx_th_fmt_005fmessage_005f4_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_fmt_005fmessage_005f4, _jsp_getInstanceManager(), _jspx_th_fmt_005fmessage_005f4_reused);
    }
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f5(jakarta.servlet.jsp.tagext.JspTag _jspx_th_fmt_005fbundle_005f1, jakarta.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    jakarta.servlet.jsp.PageContext pageContext = _jspx_page_context;
    //  fmt:message
    org.apache.taglibs.standard.tag.rt.fmt.MessageTag _jspx_th_fmt_005fmessage_005f5 = (org.apache.taglibs.standard.tag.rt.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.rt.fmt.MessageTag.class);
    boolean _jspx_th_fmt_005fmessage_005f5_reused = false;
    try {
      _jspx_th_fmt_005fmessage_005f5.setPageContext(_jspx_page_context);
      _jspx_th_fmt_005fmessage_005f5.setParent((jakarta.servlet.jsp.tagext.Tag) _jspx_th_fmt_005fbundle_005f1);
      // /WEB-INF/views/include/menu.jsp(34,9) name = key type = null reqTime = true required = false fragment = false deferredValue = false deferredMethod = false expectedTypeName = null methodSignature = null 
      _jspx_th_fmt_005fmessage_005f5.setKey("contact");
      int _jspx_eval_fmt_005fmessage_005f5 = _jspx_th_fmt_005fmessage_005f5.doStartTag();
      if (_jspx_th_fmt_005fmessage_005f5.doEndTag() == jakarta.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f5);
      _jspx_th_fmt_005fmessage_005f5_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_fmt_005fmessage_005f5, _jsp_getInstanceManager(), _jspx_th_fmt_005fmessage_005f5_reused);
    }
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f6(jakarta.servlet.jsp.tagext.JspTag _jspx_th_fmt_005fbundle_005f1, jakarta.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    jakarta.servlet.jsp.PageContext pageContext = _jspx_page_context;
    //  fmt:message
    org.apache.taglibs.standard.tag.rt.fmt.MessageTag _jspx_th_fmt_005fmessage_005f6 = (org.apache.taglibs.standard.tag.rt.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.rt.fmt.MessageTag.class);
    boolean _jspx_th_fmt_005fmessage_005f6_reused = false;
    try {
      _jspx_th_fmt_005fmessage_005f6.setPageContext(_jspx_page_context);
      _jspx_th_fmt_005fmessage_005f6.setParent((jakarta.servlet.jsp.tagext.Tag) _jspx_th_fmt_005fbundle_005f1);
      // /WEB-INF/views/include/menu.jsp(39,9) name = key type = null reqTime = true required = false fragment = false deferredValue = false deferredMethod = false expectedTypeName = null methodSignature = null 
      _jspx_th_fmt_005fmessage_005f6.setKey("logout");
      int _jspx_eval_fmt_005fmessage_005f6 = _jspx_th_fmt_005fmessage_005f6.doStartTag();
      if (_jspx_th_fmt_005fmessage_005f6.doEndTag() == jakarta.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f6);
      _jspx_th_fmt_005fmessage_005f6_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_fmt_005fmessage_005f6, _jsp_getInstanceManager(), _jspx_th_fmt_005fmessage_005f6_reused);
    }
    return false;
  }

  private boolean _jspx_meth_c_005fforEach_005f0(jakarta.servlet.jsp.tagext.JspTag _jspx_th_fmt_005fbundle_005f0, jakarta.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    jakarta.servlet.jsp.PageContext pageContext = _jspx_page_context;
    jakarta.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    boolean _jspx_th_c_005fforEach_005f0_reused = false;
    try {
      _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
      _jspx_th_c_005fforEach_005f0.setParent((jakarta.servlet.jsp.tagext.Tag) _jspx_th_fmt_005fbundle_005f0);
      // /WEB-INF/views/include/status.jsp(5,1) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false deferredMethod = false expectedTypeName = null methodSignature = null 
      _jspx_th_c_005fforEach_005f0.setVar("msg");
      // /WEB-INF/views/include/status.jsp(5,1) name = items type = jakarta.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true deferredMethod = false expectedTypeName = java.lang.Object methodSignature = null 
      _jspx_th_c_005fforEach_005f0.setItems(new org.apache.jasper.el.JspValueExpression("/WEB-INF/views/include/status.jsp(5,1) '${sesStatusMessageList }'",_jsp_getExpressionFactory().createValueExpression(_jspx_page_context.getELContext(),"${sesStatusMessageList }",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
      int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
      try {
        int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
        if (_jspx_eval_c_005fforEach_005f0 != jakarta.servlet.jsp.tagext.Tag.SKIP_BODY) {
          do {
            out.write("\r\n");
            out.write("		<div>");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ msg}", java.lang.String.class, (jakarta.servlet.jsp.PageContext)_jspx_page_context, null));
            out.write("</div>\r\n");
            out.write("	");
            int evalDoAfterBody = _jspx_th_c_005fforEach_005f0.doAfterBody();
            if (evalDoAfterBody != jakarta.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
              break;
          } while (true);
        }
        if (_jspx_th_c_005fforEach_005f0.doEndTag() == jakarta.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          return true;
        }
      } catch (java.lang.Throwable _jspx_exception) {
        while (_jspx_push_body_count_c_005fforEach_005f0[0]-- > 0)
          out = _jspx_page_context.popBody();
        _jspx_th_c_005fforEach_005f0.doCatch(_jspx_exception);
      } finally {
        _jspx_th_c_005fforEach_005f0.doFinally();
      }
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f0);
      _jspx_th_c_005fforEach_005f0_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_c_005fforEach_005f0, _jsp_getInstanceManager(), _jspx_th_c_005fforEach_005f0_reused);
    }
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f7(jakarta.servlet.jsp.tagext.JspTag _jspx_th_fmt_005fbundle_005f0, jakarta.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    jakarta.servlet.jsp.PageContext pageContext = _jspx_page_context;
    //  fmt:message
    org.apache.taglibs.standard.tag.rt.fmt.MessageTag _jspx_th_fmt_005fmessage_005f7 = (org.apache.taglibs.standard.tag.rt.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.rt.fmt.MessageTag.class);
    boolean _jspx_th_fmt_005fmessage_005f7_reused = false;
    try {
      _jspx_th_fmt_005fmessage_005f7.setPageContext(_jspx_page_context);
      _jspx_th_fmt_005fmessage_005f7.setParent((jakarta.servlet.jsp.tagext.Tag) _jspx_th_fmt_005fbundle_005f0);
      // /WEB-INF/views/event-tmpl-create.jsp(27,3) name = key type = null reqTime = true required = false fragment = false deferredValue = false deferredMethod = false expectedTypeName = null methodSignature = null 
      _jspx_th_fmt_005fmessage_005f7.setKey("event-template.entity");
      int _jspx_eval_fmt_005fmessage_005f7 = _jspx_th_fmt_005fmessage_005f7.doStartTag();
      if (_jspx_th_fmt_005fmessage_005f7.doEndTag() == jakarta.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f7);
      _jspx_th_fmt_005fmessage_005f7_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_fmt_005fmessage_005f7, _jsp_getInstanceManager(), _jspx_th_fmt_005fmessage_005f7_reused);
    }
    return false;
  }
}
