package dk.schioler.economy.web.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import dk.schioler.economy.web.view.mainview.MainView;

@PageTitle("data1")	 
@Route(value="data1", layout = MainView.class)
public class IndexView extends HorizontalLayout {
	
	private static final long serialVersionUID = 1L;
	
	private TextField txtFldBloodyName;
	private Button pressMeBtn;

	public IndexView() {
		setId("data1-view");
		txtFldBloodyName = new TextField("Bloody Name");
		pressMeBtn = new Button("Press Me");
		add(txtFldBloodyName, pressMeBtn);
		setVerticalComponentAlignment(Alignment.END, txtFldBloodyName, pressMeBtn);

		// Handle clicks
		pressMeBtn.addClickListener(e -> {
			Notification.show("Hello " + txtFldBloodyName.getValue());
		});
	}

}
