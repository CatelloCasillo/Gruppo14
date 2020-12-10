/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommonComponents.CommonTableElements;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Catello
 */
public class NoEditableTableModel extends DefaultTableModel{

        public NoEditableTableModel(Object[][] os, Object[] os1) {
            super(os, os1);
        }
        
        @Override
        public boolean isCellEditable(int i, int i1) {
            return false; 
        }
    }
