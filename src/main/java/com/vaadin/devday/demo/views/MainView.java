package com.vaadin.devday.demo.views;

import com.vaadin.devday.demo.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;

@Route(value = MainView.ROUTE, layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class MainView extends VerticalLayout implements HasUrlParameter<String> {

	public static final String ROUTE = "layouts";
	public static final String TITLE = "Layouts";

	private final HorizontalLayout layout;
	private Div navigation;
	private Div content;

	public MainView() {
		setPadding(false);
		setSpacing(false);
		setDefaultHorizontalComponentAlignment(Alignment.STRETCH);

		final Div header = new Div();
		header.getStyle().set("flexShrink", "0");
		header.setText("This is the header. My height is 150 pixels");
		header.setClassName("header");
		header.setHeight("150px");

		layout = new HorizontalLayout();
		layout.setSpacing(false);
		createTextLayout();

		final Div footer = new Div();
		footer.getStyle().set("flexShrink", "0");
		footer.setText("This is the footer area. My height is 100 pixels");
		footer.setClassName("footer");
		footer.setHeight("100px");

		add(header);
		add(layout);
		add(footer);

		expand(layout);
	}

	private void createTextLayout() {
		navigation = new Div();
		navigation.setClassName("navigation");
		navigation.setWidth("25%");
		navigation.getElement().getStyle().set("flex-shrink", "0");
		navigation.setText("This is the navigation area. My width is 25% of the window.");

		content = new Div();
		content.getStyle().set("display", "flex");
		content.setText("This is the content area");
		content.setClassName("content");
		content.getStyle().set("alignContent", "start");

		layout.add(navigation, content);
		layout.expand(content);
		layout.setDefaultVerticalComponentAlignment(Alignment.STRETCH);
	}

	/**
	 * Ignore this method for now.
	 *
	 * @return
	 */
	private Div createBlock() {
		final Div button = new Div();
		button.setText("Block");
		button.getStyle().set("background", "var(--lumo-tint-10pct)");
		button.setHeight("200px");
		button.setWidth("200px");
		button.getStyle().set("margin", "2px");
		return button;
	}


	@Override
	public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
		System.out.println(event.getLocation().getPath());
		if ("scroll".equals(parameter)) {
			updateUIForScroll();
		}
	}

	private void updateUIForScroll() {
		final Button add = new Button("Add", e -> {
			content.add(createBlock());
		});
		navigation.setText(null);
		content.setText(null);

		navigation.add(add);

		makeContentScrollable();

	}

	private void makeContentScrollable() {
		content.getStyle().set("flexWrap", "wrap");
		content.getStyle().set("overflowY", "auto");
	}
}