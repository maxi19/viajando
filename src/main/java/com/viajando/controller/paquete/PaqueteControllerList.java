package com.viajando.controller.paquete;


@WebServlet("/destinos")
public class PaqueteControllerList extends HttpServlet  {

	private static final long serialVersionUID = 1L;
    
    private PaqueteService paqueteService = new PaqueteServiceImp();
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DestinoDao dao = new DestinoDao();

		try {
			List<Destino> destinos = dao.list();
			Gson json = new Gson();
			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("utf-8");
			out.print(json.toJson(destinos));
			out.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
}
