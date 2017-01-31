/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_formation.ui;

import gestion_formation.model.DAO.ECFDAO;
import gestion_formation.model.DAO.FormationDAO;
import gestion_formation.model.DAO.PersonneDAO;
import gestion_formation.model.DAO.ResultatDAO;
import gestion_formation.model.DAO.StagiaireDAO;
import gestion_formation.model.ECF;
import gestion_formation.model.Formation;
import gestion_formation.model.Resultat;
import gestion_formation.model.Stagiaire;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author vanel
 */
public class MainWindow extends javax.swing.JFrame {
    
    StagiaireTableModel stgTableModel = new StagiaireTableModel();
    ECFListModel ECFListModel = new ECFListModel();
    Formation selectedFormation;
    FormationModel formTableModel = new FormationModel();

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        this.setTitle("Gestionnaire de formations");
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        initComponents();
        
        jTable_Stagiaires.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {//This line prevents double events
                    if (jTable_Stagiaires.getSelectedRow() > -1) {
                        
                        ECFListModel.clear();
                        
                        Stagiaire stg = stgTableModel.getStagiaire(jTable_Stagiaires.getSelectedRow());
                        
                        jButton_edit_stagiaire.setEnabled(true);
                        jButton_suppres_stagiaire.setEnabled(true);
                        jList_ECF_From_Formation.setEnabled(true);
                        List<ECF> ecf = ECFDAO.findAllinFormation(stg.getForm());
                        ECFListModel.addECFList(ecf);
                        jList_ECF_From_Formation.setSelectedIndex(-1);
                        
                    }
                }
            }
            
        });
        jList_ECF_formations_tab.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                
                if (jList_ECF_formations_tab.getSelectedIndex() > -1) {
                    jButton_Edit_ECF.setEnabled(true);
                    jButton_Delete_ECF.setEnabled(true);
                    int index = jList_ECF_formations_tab.getSelectedIndex();
                    
                    ECF currentECF = ECFListModel.getECF(index);
                    
                    jTextField_ECF_Description.setText(currentECF.getDescription());
                    
                } else {
                    jTextField_ECF_Description.setText("Selectionnez un ECF.");
                }
                
            }
        });
        
        jList_ECF_From_Formation.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (jList_ECF_From_Formation.getSelectedIndex() > -1) {
                    
                    if (!e.getValueIsAdjusting()) {//This line prevents double events

                        if (jToggleButton_Valid_ECF.isEnabled()) {
                            jToggleButton_Valid_ECF.setEnabled(false);
                        }
                        
                        Stagiaire stg = stgTableModel.getStagiaire(jTable_Stagiaires.getSelectedRow());
                        
                        System.out.print(jList_ECF_From_Formation.getSelectedIndex());
                        System.out.println((ECF) jList_ECF_From_Formation.getSelectedValue());
                        
                        Resultat rslt = ResultatDAO.getResultatinECF(stg, (ECF) jList_ECF_From_Formation.getSelectedValue());
                        
                        if (!rslt.isValide()) {
                            jToggleButton_Valid_ECF.setEnabled(true);
                        }
                    }
                }
            }
        });
        jToggleButton_Valid_ECF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //      selectedFormation = formModel.getFormation(JTable_Formations.getSelectedRow());

                int input = JOptionPane.showConfirmDialog(jList_ECF_From_Formation, "Attention, la validation d'un ECF est irréversible ! Confirmez ?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                
                if (input == JOptionPane.YES_OPTION) {
                    
                    Stagiaire stg = stgTableModel.getStagiaire(jTable_Stagiaires.getSelectedRow());
                    
                    Resultat rslt = ResultatDAO.getResultatinECF(stg, (ECF) jList_ECF_From_Formation.getSelectedValue());
                    
                    ResultatDAO.switchResultToValidated(rslt);
                    
                    jToggleButton_Valid_ECF.setEnabled(false);
                    
                }
                
            }
        });
        JTable_Formations.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                
                if (JTable_Formations.getSelectedRow() > -1) {
                    if (!e.getValueIsAdjusting()) {//This line prevents double events
                        selectedFormation = formTableModel.getFormation(JTable_Formations.getSelectedRow());
                        
                        ECFListModel.clear();
                        
                        jList_ECF_From_Formation.setEnabled(true);
                        List<ECF> ecf = ECFDAO.findAllinFormation(selectedFormation);
                        
                        ECFListModel.addECFList(ecf);
                        jList_ECF_From_Formation.setSelectedIndex(-1);
                        JButton_Edit_Formation.setEnabled(true);
                        jButton_Delete_Formation.setEnabled(true);
                        jButton_Add_ECF.setEnabled(true);
                        jList_ECF_formations_tab.setModel(ECFListModel);
                        jList_ECF_formations_tab.setEnabled(true);
                    }
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Tabs = new javax.swing.JTabbedPane();
        JPanel_Tab_Formations = new javax.swing.JPanel();
        JScrollPane_Formations = new javax.swing.JScrollPane();
        JTable_Formations = new javax.swing.JTable();
        jPanel_Btns_Formations = new javax.swing.JPanel();
        JButton_Edit_Formation = new javax.swing.JButton();
        jButton_Add_Formation = new javax.swing.JButton();
        jButton_Add_ECF = new javax.swing.JButton();
        jButton_Edit_ECF = new javax.swing.JButton();
        jButton_Delete_Formation = new javax.swing.JButton();
        jButton_Delete_ECF = new javax.swing.JButton();
        jScroll_ECF = new javax.swing.JScrollPane();
        jList_ECF_formations_tab = new javax.swing.JList<>();
        jPanel_ECF_Description = new javax.swing.JPanel();
        jTextField_ECF_Description = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        JPanel_tab_Stagiaires = new javax.swing.JPanel();
        jScrollPane_Stagiaire = new javax.swing.JScrollPane();
        jTable_Stagiaires = new javax.swing.JTable();
        jPanel_Add_Stagiaire = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        label_formation = new javax.swing.JLabel();
        jComboBox_formation = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField_add_stagiaire_name = new javax.swing.JTextField();
        jTextField_add_stagiaire_firstname = new javax.swing.JTextField();
        jButton_Add_Stagiaire = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jSplitPane_Stg_Manager = new javax.swing.JSplitPane();
        jPanel_Stg_Editor = new javax.swing.JPanel();
        jButton_edit_stagiaire = new javax.swing.JButton();
        jButton_suppres_stagiaire = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jPanel_ECF_handler = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList_ECF_From_Formation = new javax.swing.JList();
        jToggleButton_Valid_ECF = new javax.swing.JToggleButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu_File = new javax.swing.JMenu();
        jMenuItem_Add_Formation = new javax.swing.JMenuItem();
        jMenuItem_Add_ECF = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem_Quit = new javax.swing.JMenuItem();
        jMenu_Edit = new javax.swing.JMenu();
        jMenuItem_Edit_Formation = new javax.swing.JMenuItem();
        jMenu_Rapport = new javax.swing.JMenu();
        jMenuItem_Rapport_Formations = new javax.swing.JMenuItem();
        jMenu_Help = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Tabs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabsMouseClicked(evt);
            }
        });

        JTable_Formations.setModel(formTableModel);
        JTable_Formations.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                JTable_FormationsComponentShown(evt);
            }
        });
        JScrollPane_Formations.setViewportView(JTable_Formations);

        JButton_Edit_Formation.setText("Editer Formation");
        JButton_Edit_Formation.setEnabled(false);
        JButton_Edit_Formation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JButton_Edit_FormationActionPerformed(evt);
            }
        });

        jButton_Add_Formation.setText("Créer Formation");
        jButton_Add_Formation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Add_FormationActionPerformed(evt);
            }
        });

        jButton_Add_ECF.setText("Ajouter un ECF");
        jButton_Add_ECF.setEnabled(false);
        jButton_Add_ECF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Add_ECFActionPerformed(evt);
            }
        });

        jButton_Edit_ECF.setText("Editer un ECF");
        jButton_Edit_ECF.setEnabled(false);
        jButton_Edit_ECF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Edit_ECFActionPerformed(evt);
            }
        });

        jButton_Delete_Formation.setText("Supprimer Formation");
        jButton_Delete_Formation.setEnabled(false);
        jButton_Delete_Formation.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jButton_Delete_FormationAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jButton_Delete_Formation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Delete_FormationActionPerformed(evt);
            }
        });

        jButton_Delete_ECF.setText("Supprimer ECF");
        jButton_Delete_ECF.setEnabled(false);
        jButton_Delete_ECF.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jButton_Delete_ECFAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jButton_Delete_ECF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Delete_ECFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_Btns_FormationsLayout = new javax.swing.GroupLayout(jPanel_Btns_Formations);
        jPanel_Btns_Formations.setLayout(jPanel_Btns_FormationsLayout);
        jPanel_Btns_FormationsLayout.setHorizontalGroup(
            jPanel_Btns_FormationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Btns_FormationsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_Btns_FormationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton_Add_Formation, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                    .addComponent(jButton_Add_ECF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel_Btns_FormationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JButton_Edit_Formation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_Edit_ECF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel_Btns_FormationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton_Delete_Formation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_Delete_ECF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_Btns_FormationsLayout.setVerticalGroup(
            jPanel_Btns_FormationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Btns_FormationsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel_Btns_FormationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JButton_Edit_Formation)
                    .addComponent(jButton_Add_Formation)
                    .addComponent(jButton_Delete_Formation))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_Btns_FormationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_Btns_FormationsLayout.createSequentialGroup()
                        .addComponent(jButton_Add_ECF)
                        .addGap(5, 5, 5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Btns_FormationsLayout.createSequentialGroup()
                        .addGroup(jPanel_Btns_FormationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_Edit_ECF)
                            .addComponent(jButton_Delete_ECF))
                        .addContainerGap())))
        );

        jList_ECF_formations_tab.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Selectionner une formation" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScroll_ECF.setViewportView(jList_ECF_formations_tab);

        jTextField_ECF_Description.setEditable(false);
        jTextField_ECF_Description.setText("Selectionnez un ECF");
        jTextField_ECF_Description.setToolTipText("");

        jLabel6.setText("Description de l'ECF");

        javax.swing.GroupLayout jPanel_ECF_DescriptionLayout = new javax.swing.GroupLayout(jPanel_ECF_Description);
        jPanel_ECF_Description.setLayout(jPanel_ECF_DescriptionLayout);
        jPanel_ECF_DescriptionLayout.setHorizontalGroup(
            jPanel_ECF_DescriptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField_ECF_Description)
            .addGroup(jPanel_ECF_DescriptionLayout.createSequentialGroup()
                .addComponent(jLabel6)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel_ECF_DescriptionLayout.setVerticalGroup(
            jPanel_ECF_DescriptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_ECF_DescriptionLayout.createSequentialGroup()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_ECF_Description))
        );

        javax.swing.GroupLayout JPanel_Tab_FormationsLayout = new javax.swing.GroupLayout(JPanel_Tab_Formations);
        JPanel_Tab_Formations.setLayout(JPanel_Tab_FormationsLayout);
        JPanel_Tab_FormationsLayout.setHorizontalGroup(
            JPanel_Tab_FormationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Btns_Formations, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(JPanel_Tab_FormationsLayout.createSequentialGroup()
                .addComponent(JScrollPane_Formations, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JPanel_Tab_FormationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScroll_ECF, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
                    .addComponent(jPanel_ECF_Description, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        JPanel_Tab_FormationsLayout.setVerticalGroup(
            JPanel_Tab_FormationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanel_Tab_FormationsLayout.createSequentialGroup()
                .addGroup(JPanel_Tab_FormationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JScrollPane_Formations, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(JPanel_Tab_FormationsLayout.createSequentialGroup()
                        .addComponent(jScroll_ECF, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel_ECF_Description, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(13, 13, 13)
                .addComponent(jPanel_Btns_Formations, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        Tabs.addTab("Formations", JPanel_Tab_Formations);

        jTable_Stagiaires.setModel(stgTableModel);
        jScrollPane_Stagiaire.setViewportView(jTable_Stagiaires);

        jLabel1.setText("Ajouter un stagiaire");

        label_formation.setText("Formation :");

        jComboBox_formation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_formationActionPerformed(evt);
            }
        });

        jLabel2.setText("Nom : ");

        jLabel3.setText("Prénom : ");

        jButton_Add_Stagiaire.setText("Ajouter");
        jButton_Add_Stagiaire.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Add_StagiaireActionPerformed(evt);
            }
        });

        jButton3.setText("Annuler");

        javax.swing.GroupLayout jPanel_Add_StagiaireLayout = new javax.swing.GroupLayout(jPanel_Add_Stagiaire);
        jPanel_Add_Stagiaire.setLayout(jPanel_Add_StagiaireLayout);
        jPanel_Add_StagiaireLayout.setHorizontalGroup(
            jPanel_Add_StagiaireLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Add_StagiaireLayout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel_Add_StagiaireLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_Add_StagiaireLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_Add_StagiaireLayout.createSequentialGroup()
                        .addGroup(jPanel_Add_StagiaireLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label_formation)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel_Add_StagiaireLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_add_stagiaire_firstname)
                            .addComponent(jTextField_add_stagiaire_name)
                            .addComponent(jComboBox_formation, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(42, 42, 42))
                    .addGroup(jPanel_Add_StagiaireLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jButton_Add_Stagiaire)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addGap(76, 76, 76))))
        );
        jPanel_Add_StagiaireLayout.setVerticalGroup(
            jPanel_Add_StagiaireLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Add_StagiaireLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_Add_StagiaireLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_formation)
                    .addComponent(jComboBox_formation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_Add_StagiaireLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jTextField_add_stagiaire_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_Add_StagiaireLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jTextField_add_stagiaire_firstname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel_Add_StagiaireLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton_Add_Stagiaire)
                    .addComponent(jButton3)))
        );

        jSplitPane_Stg_Manager.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jButton_edit_stagiaire.setText("Editer fiche stagiaire");
        jButton_edit_stagiaire.setEnabled(false);
        jButton_edit_stagiaire.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_edit_stagiaireActionPerformed(evt);
            }
        });

        jButton_suppres_stagiaire.setText("Supprimer stagiaire");
        jButton_suppres_stagiaire.setEnabled(false);
        jButton_suppres_stagiaire.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_suppres_stagiaireActionPerformed(evt);
            }
        });

        jLabel5.setText("Gestion stagiaire");

        javax.swing.GroupLayout jPanel_Stg_EditorLayout = new javax.swing.GroupLayout(jPanel_Stg_Editor);
        jPanel_Stg_Editor.setLayout(jPanel_Stg_EditorLayout);
        jPanel_Stg_EditorLayout.setHorizontalGroup(
            jPanel_Stg_EditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Stg_EditorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_Stg_EditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton_suppres_stagiaire, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_edit_stagiaire, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_Stg_EditorLayout.setVerticalGroup(
            jPanel_Stg_EditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Stg_EditorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addComponent(jButton_edit_stagiaire)
                .addGap(18, 18, 18)
                .addComponent(jButton_suppres_stagiaire)
                .addGap(22, 22, 22))
        );

        jSplitPane_Stg_Manager.setLeftComponent(jPanel_Stg_Editor);

        jLabel4.setText("Résultats ECF");

        jList_ECF_From_Formation.setModel(ECFListModel);
        jScrollPane2.setViewportView(jList_ECF_From_Formation);

        jToggleButton_Valid_ECF.setText("Valider ECF");
        jToggleButton_Valid_ECF.setEnabled(false);

        javax.swing.GroupLayout jPanel_ECF_handlerLayout = new javax.swing.GroupLayout(jPanel_ECF_handler);
        jPanel_ECF_handler.setLayout(jPanel_ECF_handlerLayout);
        jPanel_ECF_handlerLayout.setHorizontalGroup(
            jPanel_ECF_handlerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ECF_handlerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_ECF_handlerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane2)
                    .addComponent(jToggleButton_Valid_ECF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(383, Short.MAX_VALUE))
        );
        jPanel_ECF_handlerLayout.setVerticalGroup(
            jPanel_ECF_handlerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ECF_handlerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton_Valid_ECF)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jSplitPane_Stg_Manager.setRightComponent(jPanel_ECF_handler);

        javax.swing.GroupLayout JPanel_tab_StagiairesLayout = new javax.swing.GroupLayout(JPanel_tab_Stagiaires);
        JPanel_tab_Stagiaires.setLayout(JPanel_tab_StagiairesLayout);
        JPanel_tab_StagiairesLayout.setHorizontalGroup(
            JPanel_tab_StagiairesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanel_tab_StagiairesLayout.createSequentialGroup()
                .addComponent(jScrollPane_Stagiaire, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel_Add_Stagiaire, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jSplitPane_Stg_Manager)
        );
        JPanel_tab_StagiairesLayout.setVerticalGroup(
            JPanel_tab_StagiairesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanel_tab_StagiairesLayout.createSequentialGroup()
                .addGroup(JPanel_tab_StagiairesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane_Stagiaire, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                    .addGroup(JPanel_tab_StagiairesLayout.createSequentialGroup()
                        .addComponent(jPanel_Add_Stagiaire, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSplitPane_Stg_Manager, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        Tabs.addTab("Stagiaires", JPanel_tab_Stagiaires);

        jMenu_File.setText("Fichier");

        jMenuItem_Add_Formation.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem_Add_Formation.setText("Nouvelle formation");
        jMenuItem_Add_Formation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_Add_FormationActionPerformed(evt);
            }
        });
        jMenu_File.add(jMenuItem_Add_Formation);

        jMenuItem_Add_ECF.setText("Ajouter un ECF");
        jMenu_File.add(jMenuItem_Add_ECF);
        jMenu_File.add(jSeparator1);

        jMenuItem_Quit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem_Quit.setText("Quitter");
        jMenuItem_Quit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_QuitActionPerformed(evt);
            }
        });
        jMenu_File.add(jMenuItem_Quit);

        jMenuBar1.add(jMenu_File);

        jMenu_Edit.setText("Edition");

        jMenuItem_Edit_Formation.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem_Edit_Formation.setText("Editer Formation");
        jMenuItem_Edit_Formation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_Edit_FormationActionPerformed(evt);
            }
        });
        jMenu_Edit.add(jMenuItem_Edit_Formation);

        jMenuBar1.add(jMenu_Edit);

        jMenu_Rapport.setText("Rapport");

        jMenuItem_Rapport_Formations.setText("Créer un rapport des formations");
        jMenu_Rapport.add(jMenuItem_Rapport_Formations);

        jMenuBar1.add(jMenu_Rapport);

        jMenu_Help.setText("?");
        jMenuBar1.add(jMenu_Help);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Tabs)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(Tabs)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void JButton_Edit_FormationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JButton_Edit_FormationActionPerformed
        int index = JTable_Formations.getSelectedRow();
        Formation form = formTableModel.getFormation(index);
        FormationForm formular = new FormationForm(this, true, formTableModel, form);
        formular.setVisible(true);

    }//GEN-LAST:event_JButton_Edit_FormationActionPerformed

    private void jMenuItem_QuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_QuitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem_QuitActionPerformed

    private void JTable_FormationsComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_JTable_FormationsComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_JTable_FormationsComponentShown

    private void jButton_Add_FormationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Add_FormationActionPerformed
        showFormationFormJDiag();
    }//GEN-LAST:event_jButton_Add_FormationActionPerformed

    private void jMenuItem_Add_FormationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_Add_FormationActionPerformed
        showFormationFormJDiag();
    }//GEN-LAST:event_jMenuItem_Add_FormationActionPerformed

    private void jMenuItem_Edit_FormationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_Edit_FormationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem_Edit_FormationActionPerformed

    private void jComboBox_formationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_formationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_formationActionPerformed

    private void jButton_Add_StagiaireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Add_StagiaireActionPerformed
        
        if (jComboBox_formation.getSelectedIndex() == -1 || jTextField_add_stagiaire_firstname.getText().isEmpty() || jTextField_add_stagiaire_name.getText().isEmpty()) {
            
            JOptionPane.showMessageDialog(this, "Certains champs ne sont pas remplis", "Erreur", JOptionPane.ERROR_MESSAGE);
            
        } else if (ECFDAO.findAllinFormation((Formation) jComboBox_formation.getSelectedItem()).isEmpty()) {
            
            JOptionPane.showMessageDialog(this, "La formation ne contient pas d'ECF", "Erreur", JOptionPane.ERROR_MESSAGE);
            
        } else {
            
            if (StagiaireDAO.getStgByFormation((Formation) jComboBox_formation.getSelectedItem()).isEmpty()) {
                
                JOptionPane.showMessageDialog(this, "Attention ! Une fois le premier stagiaire ajouté, le nombre d'ECF ne sera plus modifiable", "Ajout d'un stagiaire", JOptionPane.WARNING_MESSAGE);
                
            }
            
            int input = JOptionPane.showConfirmDialog(this, "Confirmez vous l'ajout du stagiaire à la formation ?", "Confirmation", JOptionPane.YES_NO_OPTION);
            
            if (input == JOptionPane.YES_OPTION) {
                
                Stagiaire stg = new Stagiaire((Formation) jComboBox_formation.getSelectedItem(), jTextField_add_stagiaire_name.getText(), jTextField_add_stagiaire_firstname.getText());
                int stg_id = StagiaireDAO.AddStagiaire(stg);
                
                stg.setCodeStagiaire(stg_id);
                
                if (stg.getCodeStagiaire() > 0) {
                    
                    List<ECF> listECF = ECFDAO.findAllinFormation(stg.getForm());
                    
                    for (ECF ecf : listECF) {
                        ResultatDAO.initiateStagiaireResultat(stg, ecf);
                    }
                    
                    JOptionPane.showMessageDialog(this, stg.getPrenom() + " " + stg.getNom() + " suit désormais la formation " + stg.getForm().getNom() + ".", "Information", JOptionPane.INFORMATION_MESSAGE);
                    clearForm_jPanel_Add_Stagiaire();
                    stgTableModel.addStagiaire(stg);
                    
                } else if (input == JOptionPane.NO_OPTION) {
                    
                    clearForm_jPanel_Add_Stagiaire();
                    
                }
                
            }
            
        }
        

    }//GEN-LAST:event_jButton_Add_StagiaireActionPerformed

    private void jButton_edit_stagiaireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_edit_stagiaireActionPerformed
        Stagiaire stg = stgTableModel.getStagiaire(jTable_Stagiaires.getSelectedRow());
        StagiaireFormEdit stgFormEdit = new StagiaireFormEdit(this, true, stg, stgTableModel, jTable_Stagiaires.getSelectedRow());
        stgFormEdit.setVisible(true);
    }//GEN-LAST:event_jButton_edit_stagiaireActionPerformed

    private void jButton_Add_ECFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Add_ECFActionPerformed
        if (!StagiaireDAO.getStgByFormation(selectedFormation).isEmpty()) {
            JOptionPane.showMessageDialog(jList_ECF_From_Formation, "Impossible de rajouter un ECF à une formation contenant un ou plusieurs stagiaire", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else {
            
            JOptionPane.showMessageDialog(this, "Une fois le premier stagiaire ajouté, il ne sera plus possible de rajouter des ECF", "Avertissement", JOptionPane.WARNING_MESSAGE);
            ECFForm ecfform = new ECFForm(this, true, selectedFormation, ECFListModel);
            ecfform.setVisible(true);
        }
        

    }//GEN-LAST:event_jButton_Add_ECFActionPerformed

    private void jButton_Delete_FormationAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jButton_Delete_FormationAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_Delete_FormationAncestorAdded

    private void jButton_Delete_FormationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Delete_FormationActionPerformed
        
        int index = JTable_Formations.getSelectedRow();
        Formation form = formTableModel.getFormation(index);
        
        if (!StagiaireDAO.getStgByFormation(form).isEmpty()) {
            
            JOptionPane.showMessageDialog(jList_ECF_From_Formation, "Impossible de supprimer une formation dans laquelle des stagiaires sont inscrits", "Supression impossible", JOptionPane.ERROR_MESSAGE);
            
        } else {
            int input = JOptionPane.showConfirmDialog(this, "Attention : la formation et les ECF associés seront supprimés définitivement.", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (input == JOptionPane.OK_OPTION) {
                
                    
                
                if (ECFDAO.deleteAllByFormation(form) && FormationDAO.deleteFormation(form)) {
                    
                    JOptionPane.showMessageDialog(jList_ECF_From_Formation, "Formation supprimée avec succès", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                    JButton_Edit_Formation.setEnabled(false);
                    jButton_Delete_Formation.setEnabled(false);
                    formTableModel.removeFormation(index);
                    if (jButton_Add_ECF.isEnabled()) {
                        jButton_Add_ECF.setEnabled(false);
                    }
                    
                } else {
                    
                    
                     JOptionPane.showMessageDialog(jList_ECF_From_Formation, "Erreur pendant la supression de la formation", "Supression impossible", JOptionPane.ERROR_MESSAGE);
                    
                }
            }
        }
        

    }//GEN-LAST:event_jButton_Delete_FormationActionPerformed

    private void jButton_Delete_ECFAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jButton_Delete_ECFAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_Delete_ECFAncestorAdded

    private void jButton_Delete_ECFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Delete_ECFActionPerformed
        ECF ecf = (ECF) ECFListModel.get(jList_ECF_formations_tab.getSelectedIndex());
        
        if (ECFDAO.deleteECF(ecf)) {
            
            JOptionPane.showMessageDialog(jList_ECF_From_Formation, "ECF supprimé avec succès", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            ECFListModel.remove(jList_ECF_formations_tab.getSelectedIndex());
            jList_ECF_formations_tab.setSelectedIndex(-1);
            jButton_Delete_ECF.setEnabled(false);
            jButton_Edit_ECF.setEnabled(false);
            
        } else {
            
            JOptionPane.showMessageDialog(jList_ECF_From_Formation, "Impossible de supprimer l'ECF", "Erreur", JOptionPane.ERROR_MESSAGE);
            
        }

    }//GEN-LAST:event_jButton_Delete_ECFActionPerformed

    private void jButton_suppres_stagiaireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_suppres_stagiaireActionPerformed
        
        Stagiaire stg = stgTableModel.getStagiaire(jTable_Stagiaires.getSelectedRow());
        
        if (StagiaireDAO.deleteStagiaire(stg)) {
            System.out.print(stg.getId());
            PersonneDAO.deletePersonneById(stg.getId());
            JOptionPane.showMessageDialog(jList_ECF_From_Formation, stg.getPrenom() + " " + stg.getNom() + " a retiré avec succès de la formation " + stg.getForm().getNom() + ".", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            stgTableModel.removeStagiaire(jTable_Stagiaires.getSelectedRow());
            jButton_edit_stagiaire.setEnabled(false);
            jButton_suppres_stagiaire.setEnabled(false);
            ECFListModel.clear();
            
        }
    }//GEN-LAST:event_jButton_suppres_stagiaireActionPerformed

    private void jButton_Edit_ECFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Edit_ECFActionPerformed
        int index = jList_ECF_formations_tab.getSelectedIndex();
        
        ECFForm ecfForm = new ECFForm(this, true, selectedFormation, ECFListModel, ECFListModel.getECF(index), index);
        
        ecfForm.setVisible(true);

    }//GEN-LAST:event_jButton_Edit_ECFActionPerformed

    private void TabsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabsMouseClicked
        jComboBox_formation.removeAllItems();
        List<Formation> findAllForm = FormationDAO.findAll();
        for (Formation form : findAllForm) {
            
            jComboBox_formation.addItem(form);
            
        }
        jComboBox_formation.setSelectedIndex(-1);
    }//GEN-LAST:event_TabsMouseClicked
    
    private void clearForm_jPanel_Add_Stagiaire() {
        
        jTextField_add_stagiaire_firstname.setText(null);
        jTextField_add_stagiaire_name.setText(null);
        jComboBox_formation.setSelectedIndex(-1);
        
    }
    
    private void showFormationFormJDiag() {
        
        FormationForm formationForm = new FormationForm(this, true, formTableModel);
        formationForm.setVisible(true);
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JButton_Edit_Formation;
    private javax.swing.JPanel JPanel_Tab_Formations;
    private javax.swing.JPanel JPanel_tab_Stagiaires;
    private javax.swing.JScrollPane JScrollPane_Formations;
    private javax.swing.JTable JTable_Formations;
    private javax.swing.JTabbedPane Tabs;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton_Add_ECF;
    private javax.swing.JButton jButton_Add_Formation;
    private javax.swing.JButton jButton_Add_Stagiaire;
    private javax.swing.JButton jButton_Delete_ECF;
    private javax.swing.JButton jButton_Delete_Formation;
    private javax.swing.JButton jButton_Edit_ECF;
    private javax.swing.JButton jButton_edit_stagiaire;
    private javax.swing.JButton jButton_suppres_stagiaire;
    private javax.swing.JComboBox<Formation> jComboBox_formation;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JList jList_ECF_From_Formation;
    private javax.swing.JList<String> jList_ECF_formations_tab;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem_Add_ECF;
    private javax.swing.JMenuItem jMenuItem_Add_Formation;
    private javax.swing.JMenuItem jMenuItem_Edit_Formation;
    private javax.swing.JMenuItem jMenuItem_Quit;
    private javax.swing.JMenuItem jMenuItem_Rapport_Formations;
    private javax.swing.JMenu jMenu_Edit;
    private javax.swing.JMenu jMenu_File;
    private javax.swing.JMenu jMenu_Help;
    private javax.swing.JMenu jMenu_Rapport;
    private javax.swing.JPanel jPanel_Add_Stagiaire;
    private javax.swing.JPanel jPanel_Btns_Formations;
    private javax.swing.JPanel jPanel_ECF_Description;
    private javax.swing.JPanel jPanel_ECF_handler;
    private javax.swing.JPanel jPanel_Stg_Editor;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane_Stagiaire;
    private javax.swing.JScrollPane jScroll_ECF;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JSplitPane jSplitPane_Stg_Manager;
    private javax.swing.JTable jTable_Stagiaires;
    private javax.swing.JTextField jTextField_ECF_Description;
    private javax.swing.JTextField jTextField_add_stagiaire_firstname;
    private javax.swing.JTextField jTextField_add_stagiaire_name;
    private javax.swing.JToggleButton jToggleButton_Valid_ECF;
    private javax.swing.JLabel label_formation;
    // End of variables declaration//GEN-END:variables

}
