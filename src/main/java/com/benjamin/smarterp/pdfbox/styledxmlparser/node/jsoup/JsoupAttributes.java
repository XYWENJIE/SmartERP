package com.benjamin.smarterp.pdfbox.styledxmlparser.node.jsoup;

import java.util.Iterator;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;

import com.benjamin.smarterp.pdfbox.styledxmlparser.node.IAttribute;
import com.benjamin.smarterp.pdfbox.styledxmlparser.node.IAttributes;

public class JsoupAttributes implements IAttributes {
	
	private Attributes attributes;

	public JsoupAttributes(Attributes attributes) {
		this.attributes = attributes;
	}

	@Override
	public Iterator<IAttribute> iterator() {
		return new AttributeIterator(attributes.iterator());
	}

	@Override
	public String getAttribute(String key) {
		return attributes.hasKey(key) ? attributes.get(key) : null;
	}

	@Override
	public void setAttribute(String key, String value) {
		if(attributes.hasKey(key)) {
			attributes.remove(value);
		}
		attributes.put(key,value);
	}

	@Override
	public int size() {
		return attributes.size();
	}

	
	private static class AttributeIterator implements Iterator<IAttribute> {
		
		private Iterator<Attribute> iterator;
		
		public AttributeIterator(Iterator<Attribute> iterator) {
			this.iterator = iterator;
		}

		@Override
		public boolean hasNext() {
			return iterator.hasNext();
		}

		@Override
		public IAttribute next() {
			return new JsoupAttribute(iterator.next());
		}
		
		public void remove() {
			throw new UnsupportedOperationException("remove");
		}
	}
}
