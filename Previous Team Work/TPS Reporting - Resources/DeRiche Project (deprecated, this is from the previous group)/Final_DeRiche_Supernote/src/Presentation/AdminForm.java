/*
 * CIST2931-Advanced Systems Project
 * Team 1 AKA Code Monkeys:
 *      Team Lead AKA who to blame for things: Triston Gregoire
 *      Team: Bonnie Falk, Bunmi Bamiro, Kristen Dawes, Natacha Mosala, Taylor Smith
 */
 /*
 * CIST2931-Advanced Systems Project
 * Team 2 AKA Ctrl/Alt/Delicious:
 *      Team Lead: Tyrus Skipper
 *      Team: Steven Alcorn, Zachary Weaver, Antonio Mosquera, Rupa Shrestha
 */
package Presentation;

import Business.*;
import Data_Access.NoteDBConnect;
import Data_Access.ParticipantDBConnect;
import Data_Access.UserDBConnect;
import Data_Access.GoalDBConnect;
import java.awt.Desktop;
import java.awt.Frame;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author taylorsmith
 */
public class AdminForm extends javax.swing.JFrame {

    String currentGoalParticipant = null;
    //this is my variable for the condition check at line 3302
    int loginTest;
    boolean openDocTest = false;
    int noteID;
    String comment;
    String noteComment;
    /**
     * Version information for the About tab under Help, whomever team is
     * working on this just needs to update these 3 variables with their
     * information
     */
    String teamName = "Ctr/Alt/Delicious";
    String version = "Snowflake";
    String semester = "Fall 2016";
    /**
     *
     */
    public static String activeUser = null;
    ParticipantLogic pl = new ParticipantLogic();
    UserLogic ul = new UserLogic();

    private static DefaultListModel<String> participantModel = new DefaultListModel<>();
    private static DefaultListModel<String> reviewModel = new DefaultListModel<>();

    /**
     * Creates new form AdminForm
     *
     * @param user
     */
    public AdminForm(String user) {
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //this.setVisible(true);
        initComponents();
        setLocationRelativeTo(null);
        dialog_downloadFile.setLocationRelativeTo(null);
        dialog_uploadFile.setLocationRelativeTo(null);
        dialog_submitNote.setLocationRelativeTo(null);
        dialog_addComments.setLocationRelativeTo(null);
        dialog_editNote.setLocationRelativeTo(null);
        dialog_editTime.setLocationRelativeTo(null);
        dialog_rejectTime.setLocationRelativeTo(null);
        UserDBConnect uc = new UserDBConnect();

        txt_goalDescription1.setEditable(false);
        txt_goalDescription2.setEditable(false);
        txt_goalDescription3.setEditable(false);
        txt_goalDescription4.setEditable(false);
        txt_goalDescription5.setEditable(false);

        try {
            //These all populate the dropdowns and jlists when the window first opens
            populateComboParticipant(pl.populate());
            populateComboSelectUser(UserDBConnect.populateUser());
            populateComboGoal();
            populateParticipantJList(ParticipantDBConnect.selectAllParticipants());
            populateParticipantNoteReviewJList(NoteLogic.populateNotesPendingReview());

        } catch (SQLException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        activeUser = user;
        try {
            ul = ul.select(activeUser);
            //Checks the clearance of the user who logged in and displays only the tabs they have permission to see.
            switch (ul.getClearance()) {
                case 0:
                    //Admin sees everything
                    loginTest = 0;
                    break;
                case 1:
                    //Reviewer
                    System.err.println(tabPane_container.getTabCount());

                    tabPane_container.remove(7);
                    tabPane_container.remove(4);
                    tabPane_container.remove(3);
                    tabPane_container.remove(2);
                    tabPane_container.remove(1);
                    break;
                case 2:
                    //DCP
                    //RUPA COMMENTED THIS OUT
                    tabPane_container.remove(7);
                    tabPane_container.remove(6);
                    tabPane_container.remove(3);
                    tabPane_container.remove(2);
                    tabPane_container.remove(1);
                    tabPane_container.remove(0);
                    loginTest = 1;
                    break;
                case 3:
                    //Auditor not yet implemented
                    //RUPA IMPLEMENTED
                    //tabPane_container.remove(7);
                    tabPane_container.remove(6);
                    tabPane_container.remove(4);
                    tabPane_container.remove(3);
                    tabPane_container.remove(2);
                    tabPane_container.remove(1);
                    tabPane_container.remove(0);                    

                    break;
                case 4:
                    tabPane_container.removeAll();
                    //User has been blocked by Admin
                    this.dispose();
                    break;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ArrayIndexOutOfBoundsException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroup_yesNo1 = new javax.swing.ButtonGroup();
        btnGroup_yesNo2 = new javax.swing.ButtonGroup();
        btnGroup_yesNo3 = new javax.swing.ButtonGroup();
        btnGroup_yesNo4 = new javax.swing.ButtonGroup();
        btnGroup_yesNo5 = new javax.swing.ButtonGroup();
        dialog_uploadFile = new javax.swing.JDialog();
        file_upload = new javax.swing.JFileChooser();
        dialog_downloadFile = new javax.swing.JDialog();
        file_download = new javax.swing.JFileChooser();
        dialog_submitNote = new javax.swing.JDialog();
        btn_submitSubmit = new javax.swing.JButton();
        btn_submitCancel = new javax.swing.JButton();
        lbl_noteSubmissionInstructions = new javax.swing.JLabel();
        lbl_noteSubmitFurtherInstructions = new javax.swing.JLabel();
        scroll_noteComment = new javax.swing.JScrollPane();
        txt_noteComment = new javax.swing.JTextArea();
        dialog_addComments = new javax.swing.JDialog();
        scroll_rejectNoteComment = new javax.swing.JScrollPane();
        txt_rejectNoteComment = new javax.swing.JTextArea();
        btn_cancelComments = new javax.swing.JButton();
        btn_acceptAddComments = new javax.swing.JButton();
        lbl_addComments = new javax.swing.JLabel();
        dialog_rejectTime = new javax.swing.JDialog();
        lbl_rejectTime = new javax.swing.JLabel();
        scroll_rejectedTime = new javax.swing.JScrollPane();
        txt_rejectedTime = new javax.swing.JTextArea();
        btn_cancelReject = new javax.swing.JButton();
        btn_confirmReject = new javax.swing.JButton();
        dialog_editTime = new javax.swing.JDialog();
        lbl_editTime = new javax.swing.JLabel();
        btn_cancelTimeChange = new javax.swing.JButton();
        btn_confirmTimeChange = new javax.swing.JButton();
        dialog_editNote = new javax.swing.JDialog();
        scroll_editNote = new javax.swing.JScrollPane();
        txt_editNote = new javax.swing.JTextArea();
        btn_cancel = new javax.swing.JButton();
        btn_confirm = new javax.swing.JButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        tabPane_container = new javax.swing.JTabbedPane();
        panel_noteReview = new javax.swing.JPanel();
        lbl_sortNotesBy = new javax.swing.JLabel();
        btn_sortByDate = new javax.swing.JButton();
        btn_sortByUser = new javax.swing.JButton();
        btn_sortByParticipant = new javax.swing.JButton();
        scroll_selectNoteToReview = new javax.swing.JScrollPane();
        list_selectNoteToReview = new JList(reviewModel);
        scroll_noteToBeReviewed = new javax.swing.JScrollPane();
        txt_noteToBeReviewed = new javax.swing.JTextArea();
        lbl_noteToBeReviewed = new javax.swing.JLabel();
        btn_approveNote = new javax.swing.JButton();
        btn_rejectNote = new javax.swing.JButton();
        lbl_previousNotes = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_noteComments = new javax.swing.JTextArea();
        panel_participants = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btn_updateParticipant = new javax.swing.JButton();
        btn_addParticipant = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        combo_participantSelect = new javax.swing.JComboBox<>();
        txt_participantID = new javax.swing.JTextField();
        txt_participantFirstName = new javax.swing.JTextField();
        txt_participantLastName = new javax.swing.JTextField();
        check_medicaid = new javax.swing.JCheckBox();
        check_selfPay = new javax.swing.JCheckBox();
        check_seizure = new javax.swing.JCheckBox();
        check_bm = new javax.swing.JCheckBox();
        lbl_mandatoryForm = new javax.swing.JLabel();
        check_repositioning = new javax.swing.JCheckBox();
        lbl_participantLastName = new javax.swing.JLabel();
        lbl_participantFirstName = new javax.swing.JLabel();
        lbl_medicaidNumber = new javax.swing.JLabel();
        lbl_insurance = new javax.swing.JLabel();
        panel_goals = new javax.swing.JPanel();
        panel_participantGoals = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        txt_goalDescription = new javax.swing.JTextArea();
        jScrollPane8 = new javax.swing.JScrollPane();
        txt_objectiveDescription = new javax.swing.JTextArea();
        lbl_goalDescription = new javax.swing.JLabel();
        lbl_objective = new javax.swing.JLabel();
        combo_frequency = new javax.swing.JComboBox<>();
        combo_interval = new javax.swing.JComboBox<>();
        lbl_frequency = new javax.swing.JLabel();
        lbl_interval = new javax.swing.JLabel();
        combo_goalGoalSelect = new javax.swing.JComboBox<>();
        combo_goalPartSelect = new javax.swing.JComboBox<>();
        lbl_guidanceNotes = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        txt_guidanceNotes = new javax.swing.JTextArea();
        btn_goalAdd = new javax.swing.JButton();
        btn_goalUpdate = new javax.swing.JButton();
        btn_goalRemove = new javax.swing.JButton();
        panel_users = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        combo_userLevel = new javax.swing.JComboBox<>();
        txt_userLastName = new javax.swing.JTextField();
        txt_userFirstName = new javax.swing.JTextField();
        txt_userPass = new javax.swing.JTextField();
        txt_userID = new javax.swing.JTextField();
        combo_selectUser = new javax.swing.JComboBox<>();
        btn_updateUser = new javax.swing.JButton();
        btn_deleteUser = new javax.swing.JButton();
        btn_newUser = new javax.swing.JButton();
        lbl_username = new javax.swing.JLabel();
        lbl_userPass = new javax.swing.JLabel();
        lbl_userFirstName = new javax.swing.JLabel();
        lbl_userLastName = new javax.swing.JLabel();
        lbl_userLevel = new javax.swing.JLabel();
        btn_viewUser = new javax.swing.JButton();
        lbl_userTitle = new javax.swing.JLabel();
        txt_userTitle = new javax.swing.JTextField();
        panel_noteWriting = new javax.swing.JPanel();
        panel_noteWritingGoals = new javax.swing.JPanel();
        panel_goal01 = new javax.swing.JPanel();
        jsp_goalDescription1 = new javax.swing.JScrollPane();
        txt_goalDescription1 = new javax.swing.JTextArea();
        radio_goal1Yes = new javax.swing.JRadioButton();
        radio_goal1No = new javax.swing.JRadioButton();
        combo_promptingLevel1 = new javax.swing.JComboBox<>();
        btn_appendGoal1 = new javax.swing.JButton();
        panel_goal02 = new javax.swing.JPanel();
        jsp_goalDescription2 = new javax.swing.JScrollPane();
        txt_goalDescription2 = new javax.swing.JTextArea();
        radio_goal2Yes = new javax.swing.JRadioButton();
        radio_goal2No = new javax.swing.JRadioButton();
        combo_promptingLevel2 = new javax.swing.JComboBox<>();
        btn_appendGoal2 = new javax.swing.JButton();
        panel_goal03 = new javax.swing.JPanel();
        jsp_goalDescription3 = new javax.swing.JScrollPane();
        txt_goalDescription3 = new javax.swing.JTextArea();
        radio_goal3No = new javax.swing.JRadioButton();
        radio_goal3Yes = new javax.swing.JRadioButton();
        combo_promptingLevel3 = new javax.swing.JComboBox<>();
        btn_appendGoal3 = new javax.swing.JButton();
        panel_goal04 = new javax.swing.JPanel();
        jsp_goalDescription4 = new javax.swing.JScrollPane();
        txt_goalDescription4 = new javax.swing.JTextArea();
        radio_goal4Yes = new javax.swing.JRadioButton();
        radio_goal4No = new javax.swing.JRadioButton();
        combo_promptingLevel4 = new javax.swing.JComboBox<>();
        btn_appendGoal4 = new javax.swing.JButton();
        panel_goal05 = new javax.swing.JPanel();
        jsp_goalDescription5 = new javax.swing.JScrollPane();
        txt_goalDescription5 = new javax.swing.JTextArea();
        radio_goal5Yes = new javax.swing.JRadioButton();
        radio_goal5No = new javax.swing.JRadioButton();
        combo_promptingLevel5 = new javax.swing.JComboBox<>();
        btn_appendGoal5 = new javax.swing.JButton();
        combo_noteChooseParticipant = new javax.swing.JComboBox<>();
        lbl_noteChooseParticipant = new javax.swing.JLabel();
        lbl_todayDate = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        txt_noteContent = new javax.swing.JTextArea();
        btn_saveNote = new javax.swing.JButton();
        btn_submitNote = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        check_formAbsence = new javax.swing.JCheckBox();
        check_formCCA = new javax.swing.JCheckBox();
        check_formNone = new javax.swing.JCheckBox();
        check_formSeizure = new javax.swing.JCheckBox();
        check_formIncident = new javax.swing.JCheckBox();
        check_formBodyCheck = new javax.swing.JCheckBox();
        check_formBloodPressure = new javax.swing.JCheckBox();
        check_formBowelMovement = new javax.swing.JCheckBox();
        check_formReposition = new javax.swing.JCheckBox();
        btn_displayCalendar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        lbl_goalPromptLevels = new javax.swing.JLabel();
        btn_newNote = new javax.swing.JButton();
        panel_myAccount = new javax.swing.JPanel();
        panel_updatePassword = new javax.swing.JPanel();
        lbl_enterOldPassword = new javax.swing.JLabel();
        lbl_enterNewPassword = new javax.swing.JLabel();
        lbl_confirmNewPassword = new javax.swing.JLabel();
        btn_cancelNewPass = new javax.swing.JButton();
        btn_confirmNewPass = new javax.swing.JButton();
        pass_enterOldPass = new javax.swing.JPasswordField();
        pass_enterNewPass = new javax.swing.JPasswordField();
        pass_confirmNewPass = new javax.swing.JPasswordField();
        panel_mySavedNotes = new javax.swing.JPanel();
        combo_selectNoteToOpen = new javax.swing.JComboBox<>();
        lbl_selectNoteToOpen = new javax.swing.JLabel();
        btn_openNote = new javax.swing.JButton();
        btn_deleteNote = new javax.swing.JButton();
        panel_files = new javax.swing.JPanel();
        panel_fileManaging = new javax.swing.JPanel();
        btn_uploadFile = new javax.swing.JButton();
        lbl_uploadFiles = new javax.swing.JLabel();
        lbl_downloadFiles = new javax.swing.JLabel();
        btn_downloadFiles = new javax.swing.JButton();
        panel_timeReview = new javax.swing.JPanel();
        scroll_timeReviewParticipant = new javax.swing.JScrollPane();
        list_timeReviewParticipant = new JList(participantModel);
        lbl_listOfTimes = new javax.swing.JLabel();
        scroll_timeReviewNote = new javax.swing.JScrollPane();
        txt_timeReviewNote = new javax.swing.JTextArea();
        lbl_noteLabel = new javax.swing.JLabel();
        btn_rejectTime = new javax.swing.JButton();
        btn_editTime = new javax.swing.JButton();
        btn_approveTime = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        lbl_StartDate = new javax.swing.JLabel();
        lbl_EndDate = new javax.swing.JLabel();
        btn_Print = new javax.swing.JButton();
        lbl_location = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        lbl_MedicaidID = new javax.swing.JLabel();
        txt_MedicaidID = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txt_PatientName = new javax.swing.JTextField();
        lbl_PatientName = new javax.swing.JLabel();
        btn_OK = new javax.swing.JButton();
        btn_Save = new javax.swing.JButton();
        txt_StartDate = new com.toedter.calendar.JDateChooser();
        txt_EndDate = new com.toedter.calendar.JDateChooser();
        menuBar_adminForm = new javax.swing.JMenuBar();
        menu_file = new javax.swing.JMenu();
        item_logout = new javax.swing.JMenuItem();
        item_exit = new javax.swing.JMenuItem();
        menu_edit = new javax.swing.JMenu();
        item_preferences = new javax.swing.JMenuItem();
        menu_help = new javax.swing.JMenu();
        item_documentation = new javax.swing.JMenuItem();
        item_about = new javax.swing.JMenuItem();

        dialog_uploadFile.setTitle("Upload File");
        dialog_uploadFile.setMinimumSize(new java.awt.Dimension(562, 430));

        javax.swing.GroupLayout dialog_uploadFileLayout = new javax.swing.GroupLayout(dialog_uploadFile.getContentPane());
        dialog_uploadFile.getContentPane().setLayout(dialog_uploadFileLayout);
        dialog_uploadFileLayout.setHorizontalGroup(
            dialog_uploadFileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialog_uploadFileLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(file_upload, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dialog_uploadFileLayout.setVerticalGroup(
            dialog_uploadFileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialog_uploadFileLayout.createSequentialGroup()
                .addComponent(file_upload, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 24, Short.MAX_VALUE))
        );

        dialog_downloadFile.setTitle("Download File");
        dialog_downloadFile.setMinimumSize(new java.awt.Dimension(562, 430));

        javax.swing.GroupLayout dialog_downloadFileLayout = new javax.swing.GroupLayout(dialog_downloadFile.getContentPane());
        dialog_downloadFile.getContentPane().setLayout(dialog_downloadFileLayout);
        dialog_downloadFileLayout.setHorizontalGroup(
            dialog_downloadFileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialog_downloadFileLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(file_download, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dialog_downloadFileLayout.setVerticalGroup(
            dialog_downloadFileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialog_downloadFileLayout.createSequentialGroup()
                .addComponent(file_download, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 24, Short.MAX_VALUE))
        );

        dialog_submitNote.setTitle("Confirm Note Submission");
        dialog_submitNote.setMinimumSize(new java.awt.Dimension(562, 430));

        btn_submitSubmit.setText("Submit");
        btn_submitSubmit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_submitSubmitMouseClicked(evt);
            }
        });
        btn_submitSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_submitSubmitActionPerformed(evt);
            }
        });

        btn_submitCancel.setText("Cancel");
        btn_submitCancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_submitCancelMouseClicked(evt);
            }
        });

        lbl_noteSubmissionInstructions.setText("Add a comment to your note (not required).");

        lbl_noteSubmitFurtherInstructions.setText("When you're ready, submit your note or return to edit it.");

        txt_noteComment.setColumns(20);
        txt_noteComment.setLineWrap(true);
        txt_noteComment.setRows(5);
        scroll_noteComment.setViewportView(txt_noteComment);

        javax.swing.GroupLayout dialog_submitNoteLayout = new javax.swing.GroupLayout(dialog_submitNote.getContentPane());
        dialog_submitNote.getContentPane().setLayout(dialog_submitNoteLayout);
        dialog_submitNoteLayout.setHorizontalGroup(
            dialog_submitNoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialog_submitNoteLayout.createSequentialGroup()
                .addGroup(dialog_submitNoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dialog_submitNoteLayout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(lbl_noteSubmissionInstructions))
                    .addGroup(dialog_submitNoteLayout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addGroup(dialog_submitNoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(dialog_submitNoteLayout.createSequentialGroup()
                                .addComponent(btn_submitCancel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_submitSubmit))
                            .addComponent(scroll_noteComment, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_noteSubmitFurtherInstructions))))
                .addGap(0, 104, Short.MAX_VALUE))
        );

        dialog_submitNoteLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btn_submitCancel, btn_submitSubmit});

        dialog_submitNoteLayout.setVerticalGroup(
            dialog_submitNoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dialog_submitNoteLayout.createSequentialGroup()
                .addContainerGap(54, Short.MAX_VALUE)
                .addComponent(lbl_noteSubmissionInstructions)
                .addGap(18, 18, 18)
                .addComponent(scroll_noteComment, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(lbl_noteSubmitFurtherInstructions)
                .addGap(18, 18, 18)
                .addGroup(dialog_submitNoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_submitCancel)
                    .addComponent(btn_submitSubmit))
                .addGap(82, 82, 82))
        );

        dialog_addComments.setTitle("Add Comments");

        txt_rejectNoteComment.setColumns(20);
        txt_rejectNoteComment.setLineWrap(true);
        txt_rejectNoteComment.setRows(5);
        scroll_rejectNoteComment.setViewportView(txt_rejectNoteComment);

        btn_cancelComments.setText("Cancel");
        btn_cancelComments.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_cancelCommentsMouseClicked(evt);
            }
        });

        btn_acceptAddComments.setText("Accept");
        btn_acceptAddComments.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_acceptAddCommentsMouseClicked(evt);
            }
        });

        lbl_addComments.setText("Comments:");

        javax.swing.GroupLayout dialog_addCommentsLayout = new javax.swing.GroupLayout(dialog_addComments.getContentPane());
        dialog_addComments.getContentPane().setLayout(dialog_addCommentsLayout);
        dialog_addCommentsLayout.setHorizontalGroup(
            dialog_addCommentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialog_addCommentsLayout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addComponent(btn_cancelComments)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_acceptAddComments)
                .addGap(103, 103, 103))
            .addGroup(dialog_addCommentsLayout.createSequentialGroup()
                .addGroup(dialog_addCommentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dialog_addCommentsLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(scroll_rejectNoteComment, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(dialog_addCommentsLayout.createSequentialGroup()
                        .addGap(166, 166, 166)
                        .addComponent(lbl_addComments)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        dialog_addCommentsLayout.setVerticalGroup(
            dialog_addCommentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialog_addCommentsLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lbl_addComments)
                .addGap(18, 18, 18)
                .addComponent(scroll_rejectNoteComment, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(dialog_addCommentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_cancelComments)
                    .addComponent(btn_acceptAddComments))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        dialog_rejectTime.setTitle("Reject Time");
        dialog_rejectTime.setMinimumSize(new java.awt.Dimension(400, 300));

        lbl_rejectTime.setText("Please explain why the time was rejected.");

        txt_rejectedTime.setColumns(20);
        txt_rejectedTime.setRows(5);
        scroll_rejectedTime.setViewportView(txt_rejectedTime);

        btn_cancelReject.setText("Cancel");
        btn_cancelReject.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_cancelRejectMouseClicked(evt);
            }
        });

        btn_confirmReject.setText("Confirm");
        btn_confirmReject.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_confirmRejectMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout dialog_rejectTimeLayout = new javax.swing.GroupLayout(dialog_rejectTime.getContentPane());
        dialog_rejectTime.getContentPane().setLayout(dialog_rejectTimeLayout);
        dialog_rejectTimeLayout.setHorizontalGroup(
            dialog_rejectTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialog_rejectTimeLayout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(dialog_rejectTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dialog_rejectTimeLayout.createSequentialGroup()
                        .addGroup(dialog_rejectTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbl_rejectTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(scroll_rejectedTime))
                        .addContainerGap(72, Short.MAX_VALUE))
                    .addGroup(dialog_rejectTimeLayout.createSequentialGroup()
                        .addComponent(btn_cancelReject)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_confirmReject)
                        .addGap(63, 63, 63))))
        );
        dialog_rejectTimeLayout.setVerticalGroup(
            dialog_rejectTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialog_rejectTimeLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lbl_rejectTime)
                .addGap(18, 18, 18)
                .addComponent(scroll_rejectedTime, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(dialog_rejectTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_cancelReject)
                    .addComponent(btn_confirmReject))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        dialog_editTime.setTitle("Edit Time");

        lbl_editTime.setText("Edit times for arrival and/or departure.");

        btn_cancelTimeChange.setText("Cancel");
        btn_cancelTimeChange.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_cancelTimeChangeMouseClicked(evt);
            }
        });

        btn_confirmTimeChange.setText("Confirm");
        btn_confirmTimeChange.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_confirmTimeChangeMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout dialog_editTimeLayout = new javax.swing.GroupLayout(dialog_editTime.getContentPane());
        dialog_editTime.getContentPane().setLayout(dialog_editTimeLayout);
        dialog_editTimeLayout.setHorizontalGroup(
            dialog_editTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialog_editTimeLayout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(lbl_editTime)
                .addContainerGap(93, Short.MAX_VALUE))
            .addGroup(dialog_editTimeLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(btn_cancelTimeChange)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_confirmTimeChange)
                .addGap(53, 53, 53))
        );
        dialog_editTimeLayout.setVerticalGroup(
            dialog_editTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialog_editTimeLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(lbl_editTime)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 175, Short.MAX_VALUE)
                .addGroup(dialog_editTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_cancelTimeChange)
                    .addComponent(btn_confirmTimeChange))
                .addGap(54, 54, 54))
        );

        dialog_editNote.setTitle("Edit Note");

        txt_editNote.setColumns(20);
        txt_editNote.setRows(5);
        txt_editNote.setLineWrap(true);
        scroll_editNote.setViewportView(txt_editNote);

        btn_cancel.setText("Cancel");
        btn_cancel.setToolTipText("Cancel editing the note.");
        btn_cancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_cancelMouseClicked(evt);
            }
        });

        btn_confirm.setText("Submit");
        btn_confirm.setToolTipText("Submit the edited note.");
        btn_confirm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_confirmMouseClicked(evt);
            }
        });
        btn_confirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_confirmActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dialog_editNoteLayout = new javax.swing.GroupLayout(dialog_editNote.getContentPane());
        dialog_editNote.getContentPane().setLayout(dialog_editNoteLayout);
        dialog_editNoteLayout.setHorizontalGroup(
            dialog_editNoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialog_editNoteLayout.createSequentialGroup()
                .addGroup(dialog_editNoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dialog_editNoteLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(scroll_editNote, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(dialog_editNoteLayout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addComponent(btn_cancel)
                        .addGap(164, 164, 164)
                        .addComponent(btn_confirm)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        dialog_editNoteLayout.setVerticalGroup(
            dialog_editNoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialog_editNoteLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(scroll_editNote, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(dialog_editNoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_cancel)
                    .addComponent(btn_confirm))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SuperNote (Alpha)");
        setName("frame_superNote"); // NOI18N
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        tabPane_container.setName(""); // NOI18N
        tabPane_container.setPreferredSize(new java.awt.Dimension(980, 600));
        tabPane_container.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabPane_containerMouseClicked(evt);
            }
        });

        panel_noteReview.setEnabled(false);

        lbl_sortNotesBy.setText("Sort By:");

        btn_sortByDate.setText("Date");
        btn_sortByDate.setToolTipText("Sort notes by date.");
        btn_sortByDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sortByDateActionPerformed(evt);
            }
        });

        btn_sortByUser.setText("User");
        btn_sortByUser.setToolTipText("Sort notes by user.");
        btn_sortByUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sortByUserActionPerformed(evt);
            }
        });

        btn_sortByParticipant.setText("Participant");
        btn_sortByParticipant.setToolTipText("Sort notes by participant.");
        btn_sortByParticipant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sortByParticipantActionPerformed(evt);
            }
        });

        list_selectNoteToReview.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                list_selectNoteToReviewMouseClicked(evt);
            }
        });
        scroll_selectNoteToReview.setViewportView(list_selectNoteToReview);

        txt_noteToBeReviewed.setEditable(false);
        txt_noteToBeReviewed.setColumns(20);
        txt_noteToBeReviewed.setLineWrap(true);
        txt_noteToBeReviewed.setRows(5);
        txt_noteToBeReviewed.setWrapStyleWord(true);
        scroll_noteToBeReviewed.setViewportView(txt_noteToBeReviewed);

        lbl_noteToBeReviewed.setText("Note Review:");

        btn_approveNote.setText("Approve");
        btn_approveNote.setToolTipText("Approve of the note.");
        btn_approveNote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_approveNoteActionPerformed(evt);
            }
        });

        btn_rejectNote.setText("Reject");
        btn_rejectNote.setToolTipText("Reject the note.");
        btn_rejectNote.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_rejectNoteMouseClicked(evt);
            }
        });
        btn_rejectNote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_rejectNoteActionPerformed(evt);
            }
        });

        lbl_previousNotes.setText("Comments:");

        txt_noteComments.setEditable(false);
        txt_noteComments.setColumns(20);
        txt_noteComments.setRows(5);
        txt_noteComments.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_noteCommentsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(txt_noteComments);

        javax.swing.GroupLayout panel_noteReviewLayout = new javax.swing.GroupLayout(panel_noteReview);
        panel_noteReview.setLayout(panel_noteReviewLayout);
        panel_noteReviewLayout.setHorizontalGroup(
            panel_noteReviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_noteReviewLayout.createSequentialGroup()
                .addGroup(panel_noteReviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_noteReviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(panel_noteReviewLayout.createSequentialGroup()
                            .addGap(72, 72, 72)
                            .addComponent(lbl_sortNotesBy))
                        .addGroup(panel_noteReviewLayout.createSequentialGroup()
                            .addComponent(btn_sortByDate)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn_sortByUser)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn_sortByParticipant))
                        .addComponent(scroll_selectNoteToReview)
                        .addComponent(jScrollPane1))
                    .addGroup(panel_noteReviewLayout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(lbl_previousNotes)))
                .addGap(18, 18, 18)
                .addGroup(panel_noteReviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scroll_noteToBeReviewed, javax.swing.GroupLayout.DEFAULT_SIZE, 689, Short.MAX_VALUE)
                    .addGroup(panel_noteReviewLayout.createSequentialGroup()
                        .addGroup(panel_noteReviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_noteReviewLayout.createSequentialGroup()
                                .addComponent(btn_rejectNote)
                                .addGap(18, 18, 18)
                                .addComponent(btn_approveNote))
                            .addComponent(lbl_noteToBeReviewed))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        panel_noteReviewLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btn_approveNote, btn_rejectNote});

        panel_noteReviewLayout.setVerticalGroup(
            panel_noteReviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_noteReviewLayout.createSequentialGroup()
                .addGroup(panel_noteReviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_sortNotesBy)
                    .addComponent(lbl_noteToBeReviewed))
                .addGap(12, 12, 12)
                .addGroup(panel_noteReviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_noteReviewLayout.createSequentialGroup()
                        .addGroup(panel_noteReviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_sortByDate)
                            .addComponent(btn_sortByUser)
                            .addComponent(btn_sortByParticipant))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scroll_selectNoteToReview, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                        .addComponent(lbl_previousNotes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(scroll_noteToBeReviewed))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_noteReviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_approveNote)
                    .addComponent(btn_rejectNote))
                .addContainerGap())
        );

        tabPane_container.addTab("Note Review", panel_noteReview);

        panel_participants.setPreferredSize(new java.awt.Dimension(984, 554));

        btn_updateParticipant.setText("Update Participant");
        btn_updateParticipant.setToolTipText("Update the values of a selected participant.");
        btn_updateParticipant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateParticipantActionPerformed(evt);
            }
        });

        btn_addParticipant.setText("Add Participant");
        btn_addParticipant.setToolTipText("Add the values you typed as a new participant.");
        btn_addParticipant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addParticipantActionPerformed(evt);
            }
        });

        combo_participantSelect.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Participant Or Create New" }));
        combo_participantSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_participantSelectActionPerformed(evt);
            }
        });

        txt_participantID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_participantIDActionPerformed(evt);
            }
        });

        check_medicaid.setText("Medicaid");
        check_medicaid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_medicaidActionPerformed(evt);
            }
        });

        check_selfPay.setText("Self-Pay");
        check_selfPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_selfPayActionPerformed(evt);
            }
        });

        check_seizure.setText("Seizures");
        check_seizure.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_seizureActionPerformed(evt);
            }
        });

        check_bm.setText("BM");

        lbl_mandatoryForm.setText("Mandatory Forms");

        check_repositioning.setText("Repositioning Form");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(combo_participantSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txt_participantID, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_participantFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_participantLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(check_seizure)
                        .addComponent(check_bm)
                        .addComponent(check_repositioning)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(check_medicaid)
                        .addGap(18, 18, 18)
                        .addComponent(check_selfPay))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbl_mandatoryForm)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {combo_participantSelect, txt_participantFirstName, txt_participantID, txt_participantLastName});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(combo_participantSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(check_selfPay)
                    .addComponent(check_medicaid))
                .addGap(7, 7, 7)
                .addComponent(txt_participantID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_participantFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txt_participantLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_mandatoryForm)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(check_seizure)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(check_bm)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(check_repositioning)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        lbl_participantLastName.setText("Last Name:");

        lbl_participantFirstName.setText("First Name:");
        lbl_participantFirstName.setName(""); // NOI18N

        lbl_medicaidNumber.setText("Medicaid Number:");

        lbl_insurance.setText("Insurance:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_addParticipant)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lbl_insurance)
                        .addComponent(lbl_medicaidNumber)
                        .addComponent(lbl_participantFirstName)
                        .addComponent(lbl_participantLastName)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(btn_updateParticipant)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btn_addParticipant, btn_updateParticipant});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(lbl_insurance)
                        .addGap(18, 18, 18)
                        .addComponent(lbl_medicaidNumber)
                        .addGap(14, 14, 14)
                        .addComponent(lbl_participantFirstName)
                        .addGap(18, 18, 18)
                        .addComponent(lbl_participantLastName)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 165, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_updateParticipant)
                    .addComponent(btn_addParticipant))
                .addGap(33, 33, 33))
        );

        javax.swing.GroupLayout panel_participantsLayout = new javax.swing.GroupLayout(panel_participants);
        panel_participants.setLayout(panel_participantsLayout);
        panel_participantsLayout.setHorizontalGroup(
            panel_participantsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_participantsLayout.createSequentialGroup()
                .addContainerGap(311, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(286, 286, 286))
        );
        panel_participantsLayout.setVerticalGroup(
            panel_participantsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_participantsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(28, 28, 28))
        );

        tabPane_container.addTab("Participants", panel_participants);

        txt_goalDescription.setColumns(20);
        txt_goalDescription.setLineWrap(true);
        txt_goalDescription.setRows(5);
        txt_goalDescription.setWrapStyleWord(true);
        jScrollPane7.setViewportView(txt_goalDescription);

        txt_objectiveDescription.setColumns(20);
        txt_objectiveDescription.setLineWrap(true);
        txt_objectiveDescription.setRows(5);
        txt_objectiveDescription.setWrapStyleWord(true);
        jScrollPane8.setViewportView(txt_objectiveDescription);

        lbl_goalDescription.setText("Goal:");

        lbl_objective.setText("Objective:");

        combo_frequency.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15" }));

        combo_interval.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Week", "Month" }));
        combo_interval.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_intervalActionPerformed(evt);
            }
        });

        lbl_frequency.setText("Frequency:");

        lbl_interval.setText("Interval:");

        combo_goalGoalSelect.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select A Goal", "1", "2", "3", "4", "5" }));
        combo_goalGoalSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_goalGoalSelectActionPerformed(evt);
            }
        });

        combo_goalPartSelect.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select A Participant" }));
        combo_goalPartSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_goalPartSelectActionPerformed(evt);
            }
        });

        lbl_guidanceNotes.setText("Guidance Notes:");

        txt_guidanceNotes.setColumns(20);
        txt_guidanceNotes.setLineWrap(true);
        txt_guidanceNotes.setRows(5);
        txt_guidanceNotes.setWrapStyleWord(true);
        jScrollPane12.setViewportView(txt_guidanceNotes);

        javax.swing.GroupLayout panel_participantGoalsLayout = new javax.swing.GroupLayout(panel_participantGoals);
        panel_participantGoals.setLayout(panel_participantGoalsLayout);
        panel_participantGoalsLayout.setHorizontalGroup(
            panel_participantGoalsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_participantGoalsLayout.createSequentialGroup()
                .addGroup(panel_participantGoalsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_participantGoalsLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(lbl_frequency)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(combo_frequency, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_interval)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(combo_interval, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel_participantGoalsLayout.createSequentialGroup()
                        .addGroup(panel_participantGoalsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_participantGoalsLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lbl_goalDescription))
                            .addGroup(panel_participantGoalsLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel_participantGoalsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_objective)
                                    .addComponent(lbl_guidanceNotes)))
                            .addGroup(panel_participantGoalsLayout.createSequentialGroup()
                                .addGap(92, 92, 92)
                                .addGroup(panel_participantGoalsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(combo_goalGoalSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(combo_goalPartSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 91, Short.MAX_VALUE)))
                .addContainerGap())
        );

        panel_participantGoalsLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {combo_goalGoalSelect, combo_goalPartSelect});

        panel_participantGoalsLayout.setVerticalGroup(
            panel_participantGoalsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_participantGoalsLayout.createSequentialGroup()
                .addComponent(combo_goalPartSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(combo_goalGoalSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(panel_participantGoalsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(combo_interval, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_participantGoalsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(combo_frequency, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbl_frequency)
                        .addComponent(lbl_interval)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_goalDescription)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_objective)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_guidanceNotes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        panel_participantGoalsLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {combo_goalGoalSelect, combo_goalPartSelect});

        btn_goalAdd.setText("Add");
        btn_goalAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_goalAddActionPerformed(evt);
            }
        });

        btn_goalUpdate.setText("Update");
        btn_goalUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_goalUpdateActionPerformed(evt);
            }
        });

        btn_goalRemove.setText("Remove");
        btn_goalRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_goalRemoveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_goalsLayout = new javax.swing.GroupLayout(panel_goals);
        panel_goals.setLayout(panel_goalsLayout);
        panel_goalsLayout.setHorizontalGroup(
            panel_goalsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_goalsLayout.createSequentialGroup()
                .addGap(275, 275, 275)
                .addGroup(panel_goalsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_participantGoals, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_goalsLayout.createSequentialGroup()
                        .addComponent(btn_goalAdd)
                        .addGap(100, 100, 100)
                        .addComponent(btn_goalUpdate)
                        .addGap(87, 87, 87)
                        .addComponent(btn_goalRemove)))
                .addContainerGap(293, Short.MAX_VALUE))
        );
        panel_goalsLayout.setVerticalGroup(
            panel_goalsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_goalsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panel_participantGoals, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_goalsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_goalAdd)
                    .addComponent(btn_goalUpdate)
                    .addComponent(btn_goalRemove))
                .addGap(32, 32, 32))
        );

        tabPane_container.addTab("Goals", panel_goals);

        combo_userLevel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select A User Level", "Admin", "DCP", "Reviewer", "Auditor", "Terminated" }));
        combo_userLevel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_userLevelActionPerformed(evt);
            }
        });

        txt_userID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_userIDActionPerformed(evt);
            }
        });

        combo_selectUser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select User Or Create New" }));
        combo_selectUser.setToolTipText("");
        combo_selectUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_selectUserActionPerformed(evt);
            }
        });

        btn_updateUser.setText("Update");
        btn_updateUser.setToolTipText("Update the properties of the selected user.");
        btn_updateUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateUserActionPerformed(evt);
            }
        });

        btn_deleteUser.setText("Delete");
        btn_deleteUser.setToolTipText("Delete the selected user.");
        btn_deleteUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteUserActionPerformed(evt);
            }
        });

        btn_newUser.setText("New");
        btn_newUser.setToolTipText("Create a new user from the attributes you typed.");
        btn_newUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_newUserMouseClicked(evt);
            }
        });
        btn_newUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_newUserActionPerformed(evt);
            }
        });

        lbl_username.setText("Username:");

        lbl_userPass.setText("Password:");

        lbl_userFirstName.setText("First Name:");

        lbl_userLastName.setText("Last Name:");

        lbl_userLevel.setText("User Level:");

        btn_viewUser.setText("Clear");
        btn_viewUser.setToolTipText("Display the attributes of the selected user.");
        btn_viewUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_viewUserActionPerformed(evt);
            }
        });

        lbl_userTitle.setText("Title:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbl_userLastName)
                            .addComponent(lbl_userFirstName)
                            .addComponent(lbl_userPass)
                            .addComponent(lbl_username)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lbl_userTitle)
                                .addComponent(lbl_userLevel)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_userLastName)
                            .addComponent(txt_userFirstName)
                            .addComponent(combo_selectUser, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_userID)
                            .addComponent(txt_userPass)
                            .addComponent(combo_userLevel, 0, 227, Short.MAX_VALUE)
                            .addComponent(txt_userTitle)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(btn_viewUser, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_newUser, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_deleteUser)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_updateUser, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btn_deleteUser, btn_newUser, btn_updateUser, btn_viewUser});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(combo_selectUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_userID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_username))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_userPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_userPass))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_userFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_userFirstName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_userLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_userLastName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_userTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_userTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combo_userLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_userLevel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 176, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_newUser)
                    .addComponent(btn_deleteUser)
                    .addComponent(btn_updateUser)
                    .addComponent(btn_viewUser))
                .addContainerGap())
        );

        javax.swing.GroupLayout panel_usersLayout = new javax.swing.GroupLayout(panel_users);
        panel_users.setLayout(panel_usersLayout);
        panel_usersLayout.setHorizontalGroup(
            panel_usersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_usersLayout.createSequentialGroup()
                .addContainerGap(318, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(283, 283, 283))
        );
        panel_usersLayout.setVerticalGroup(
            panel_usersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_usersLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(125, Short.MAX_VALUE))
        );

        tabPane_container.addTab("Users", panel_users);

        panel_noteWritingGoals.setBorder(javax.swing.BorderFactory.createTitledBorder("Goals"));

        txt_goalDescription1.setColumns(10);
        txt_goalDescription1.setLineWrap(true);
        txt_goalDescription1.setRows(5);
        txt_goalDescription1.setToolTipText("");
        jsp_goalDescription1.setViewportView(txt_goalDescription1);

        btnGroup_yesNo1.add(radio_goal1Yes);
        radio_goal1Yes.setText("Yes");
        radio_goal1Yes.setToolTipText("Goal 1 accomplished today?");

        btnGroup_yesNo1.add(radio_goal1No);
        radio_goal1No.setText("No");
        radio_goal1No.setToolTipText("Goal 1 accomplished today?");

        combo_promptingLevel1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6" }));
        combo_promptingLevel1.setToolTipText("Select prompting level for goal 1.");

        btn_appendGoal1.setText("+");
        btn_appendGoal1.setToolTipText("Add this goal to your note.");
        btn_appendGoal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_appendGoal1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_goal01Layout = new javax.swing.GroupLayout(panel_goal01);
        panel_goal01.setLayout(panel_goal01Layout);
        panel_goal01Layout.setHorizontalGroup(
            panel_goal01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_goal01Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(combo_promptingLevel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_goal01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radio_goal1Yes)
                    .addComponent(radio_goal1No))
                .addGap(14, 14, 14)
                .addComponent(jsp_goalDescription1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_appendGoal1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel_goal01Layout.setVerticalGroup(
            panel_goal01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_goal01Layout.createSequentialGroup()
                .addComponent(jsp_goalDescription1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panel_goal01Layout.createSequentialGroup()
                .addGroup(panel_goal01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_goal01Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(radio_goal1Yes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radio_goal1No))
                    .addGroup(panel_goal01Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(btn_appendGoal1))
                    .addGroup(panel_goal01Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(combo_promptingLevel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txt_goalDescription2.setColumns(10);
        txt_goalDescription2.setLineWrap(true);
        txt_goalDescription2.setRows(5);
        jsp_goalDescription2.setViewportView(txt_goalDescription2);

        btnGroup_yesNo2.add(radio_goal2Yes);
        radio_goal2Yes.setText("Yes");
        radio_goal2Yes.setToolTipText("Goal 2 accomplished today?");

        btnGroup_yesNo2.add(radio_goal2No);
        radio_goal2No.setText("No");
        radio_goal2No.setToolTipText("Goal 2 accomplished today?");

        combo_promptingLevel2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6" }));
        combo_promptingLevel2.setToolTipText("Select prompting level for goal 2.");

        btn_appendGoal2.setText("+");
        btn_appendGoal2.setToolTipText("Add this goal to your note.");
        btn_appendGoal2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_appendGoal2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_goal02Layout = new javax.swing.GroupLayout(panel_goal02);
        panel_goal02.setLayout(panel_goal02Layout);
        panel_goal02Layout.setHorizontalGroup(
            panel_goal02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_goal02Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(combo_promptingLevel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_goal02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radio_goal2Yes)
                    .addComponent(radio_goal2No))
                .addGap(14, 14, 14)
                .addComponent(jsp_goalDescription2, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_appendGoal2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel_goal02Layout.setVerticalGroup(
            panel_goal02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_goal02Layout.createSequentialGroup()
                .addComponent(jsp_goalDescription2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panel_goal02Layout.createSequentialGroup()
                .addGroup(panel_goal02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_goal02Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(radio_goal2Yes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radio_goal2No))
                    .addGroup(panel_goal02Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(combo_promptingLevel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_goal02Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(btn_appendGoal2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txt_goalDescription3.setColumns(10);
        txt_goalDescription3.setLineWrap(true);
        txt_goalDescription3.setRows(5);
        jsp_goalDescription3.setViewportView(txt_goalDescription3);

        btnGroup_yesNo3.add(radio_goal3No);
        radio_goal3No.setText("No");
        radio_goal3No.setToolTipText("Goal 3 accomplished today?");

        btnGroup_yesNo3.add(radio_goal3Yes);
        radio_goal3Yes.setText("Yes");
        radio_goal3Yes.setToolTipText("Goal 3 accomplished today?");

        combo_promptingLevel3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6" }));
        combo_promptingLevel3.setToolTipText("Select prompting level for goal 3.");

        btn_appendGoal3.setText("+");
        btn_appendGoal3.setToolTipText("Add this goal to your note.");
        btn_appendGoal3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_appendGoal3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_goal03Layout = new javax.swing.GroupLayout(panel_goal03);
        panel_goal03.setLayout(panel_goal03Layout);
        panel_goal03Layout.setHorizontalGroup(
            panel_goal03Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_goal03Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(combo_promptingLevel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_goal03Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radio_goal3No)
                    .addComponent(radio_goal3Yes))
                .addGap(14, 14, 14)
                .addComponent(jsp_goalDescription3, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_appendGoal3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panel_goal03Layout.setVerticalGroup(
            panel_goal03Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_goal03Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_goal03Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(radio_goal3Yes)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(radio_goal3No)
                    .addGap(19, 19, 19))
                .addGroup(panel_goal03Layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(combo_promptingLevel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(panel_goal03Layout.createSequentialGroup()
                .addGroup(panel_goal03Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jsp_goalDescription3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_goal03Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btn_appendGoal3)))
                .addContainerGap())
        );

        txt_goalDescription4.setColumns(10);
        txt_goalDescription4.setLineWrap(true);
        txt_goalDescription4.setRows(5);
        jsp_goalDescription4.setViewportView(txt_goalDescription4);

        btnGroup_yesNo4.add(radio_goal4Yes);
        radio_goal4Yes.setText("Yes");
        radio_goal4Yes.setToolTipText("Goal 4 accomplished today?");

        btnGroup_yesNo4.add(radio_goal4No);
        radio_goal4No.setText("No");
        radio_goal4No.setToolTipText("Goal 4 accomplished today?");

        combo_promptingLevel4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6" }));
        combo_promptingLevel4.setToolTipText("Select prompting level for goal 4.");

        btn_appendGoal4.setText("+");
        btn_appendGoal4.setToolTipText("Add this goal to your note.");
        btn_appendGoal4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_appendGoal4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_goal04Layout = new javax.swing.GroupLayout(panel_goal04);
        panel_goal04.setLayout(panel_goal04Layout);
        panel_goal04Layout.setHorizontalGroup(
            panel_goal04Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_goal04Layout.createSequentialGroup()
                .addComponent(combo_promptingLevel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_goal04Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radio_goal4Yes)
                    .addComponent(radio_goal4No))
                .addGap(13, 13, 13)
                .addComponent(jsp_goalDescription4, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_appendGoal4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel_goal04Layout.setVerticalGroup(
            panel_goal04Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_goal04Layout.createSequentialGroup()
                .addGroup(panel_goal04Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jsp_goalDescription4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_goal04Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(radio_goal4Yes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radio_goal4No))
                    .addGroup(panel_goal04Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(btn_appendGoal4))
                    .addGroup(panel_goal04Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(combo_promptingLevel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        txt_goalDescription5.setColumns(10);
        txt_goalDescription5.setLineWrap(true);
        txt_goalDescription5.setRows(5);
        jsp_goalDescription5.setViewportView(txt_goalDescription5);

        btnGroup_yesNo5.add(radio_goal5Yes);
        radio_goal5Yes.setText("Yes");
        radio_goal5Yes.setToolTipText("Goal 5 accomplished today?");

        btnGroup_yesNo5.add(radio_goal5No);
        radio_goal5No.setText("No");
        radio_goal5No.setToolTipText("Goal 5 accomplished today?");

        combo_promptingLevel5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6" }));
        combo_promptingLevel5.setToolTipText("Select prompting level for goal 5.");

        btn_appendGoal5.setText("+");
        btn_appendGoal5.setToolTipText("Add this goal to your note.");
        btn_appendGoal5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_appendGoal5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_goal05Layout = new javax.swing.GroupLayout(panel_goal05);
        panel_goal05.setLayout(panel_goal05Layout);
        panel_goal05Layout.setHorizontalGroup(
            panel_goal05Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_goal05Layout.createSequentialGroup()
                .addComponent(combo_promptingLevel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_goal05Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radio_goal5Yes)
                    .addComponent(radio_goal5No))
                .addGap(13, 13, 13)
                .addComponent(jsp_goalDescription5, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_appendGoal5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel_goal05Layout.setVerticalGroup(
            panel_goal05Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_goal05Layout.createSequentialGroup()
                .addComponent(jsp_goalDescription5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panel_goal05Layout.createSequentialGroup()
                .addGroup(panel_goal05Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_goal05Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(radio_goal5Yes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radio_goal5No))
                    .addGroup(panel_goal05Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(combo_promptingLevel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_goal05Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(btn_appendGoal5)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel_noteWritingGoalsLayout = new javax.swing.GroupLayout(panel_noteWritingGoals);
        panel_noteWritingGoals.setLayout(panel_noteWritingGoalsLayout);
        panel_noteWritingGoalsLayout.setHorizontalGroup(
            panel_noteWritingGoalsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_noteWritingGoalsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_noteWritingGoalsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_goal01, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_goal02, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_goal03, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panel_noteWritingGoalsLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(panel_noteWritingGoalsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panel_goal05, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panel_goal04, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        panel_noteWritingGoalsLayout.setVerticalGroup(
            panel_noteWritingGoalsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_noteWritingGoalsLayout.createSequentialGroup()
                .addComponent(panel_goal01, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_goal02, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_goal03, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_goal04, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_goal05, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        combo_noteChooseParticipant.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No participant selected.", "-", "-", "-", "-", "-" }));
        combo_noteChooseParticipant.setToolTipText("Choose the participant your note will be about.");
        combo_noteChooseParticipant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_noteChooseParticipantActionPerformed(evt);
            }
        });

        lbl_noteChooseParticipant.setText("Participant:");

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String currentDate = dateFormat.format(date);
        lbl_todayDate.setEditable(false);
        lbl_todayDate.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lbl_todayDate.setText("DATE");
        lbl_todayDate.setText(currentDate);
        lbl_todayDate.setToolTipText("A date selector will go here.");
        lbl_todayDate.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        lbl_todayDate.setEnabled(false);

        txt_noteContent.setColumns(20);
        txt_noteContent.setLineWrap(true);
        txt_noteContent.setRows(5);
        txt_noteContent.setWrapStyleWord(true);
        jScrollPane3.setViewportView(txt_noteContent);

        btn_saveNote.setText("Save Draft");
        btn_saveNote.setToolTipText("Save this note as a draft to continue later.");
        btn_saveNote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveNoteActionPerformed(evt);
            }
        });

        btn_submitNote.setText("Submit Note");
        btn_submitNote.setToolTipText("Submite this not to be approved.");
        btn_submitNote.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_submitNoteMouseClicked(evt);
            }
        });
        btn_submitNote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_submitNoteActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Forms"));

        check_formAbsence.setText("Absence");
        check_formAbsence.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_formAbsenceActionPerformed(evt);
            }
        });

        check_formCCA.setText("CCA");
        check_formCCA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_formCCAActionPerformed(evt);
            }
        });

        check_formNone.setText("None");

        check_formSeizure.setText("Seizure");
        check_formSeizure.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_formSeizureActionPerformed(evt);
            }
        });

        check_formIncident.setText("Incident");
        check_formIncident.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_formIncidentActionPerformed(evt);
            }
        });

        check_formBodyCheck.setText("Body Check");
        check_formBodyCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_formBodyCheckActionPerformed(evt);
            }
        });

        check_formBloodPressure.setText("Blood Pressure");
        check_formBloodPressure.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_formBloodPressureActionPerformed(evt);
            }
        });

        check_formBowelMovement.setText("BM");
        check_formBowelMovement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_formBowelMovementActionPerformed(evt);
            }
        });

        check_formReposition.setText("Repositioning");
        check_formReposition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_formRepositionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(check_formAbsence)
            .addComponent(check_formCCA)
            .addComponent(check_formNone)
            .addComponent(check_formSeizure)
            .addComponent(check_formIncident)
            .addComponent(check_formBloodPressure)
            .addComponent(check_formBowelMovement)
            .addComponent(check_formBodyCheck)
            .addComponent(check_formReposition)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(check_formNone)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(check_formCCA)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(check_formBloodPressure)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(check_formBodyCheck)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(check_formBowelMovement)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(check_formReposition)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(check_formIncident)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(check_formSeizure)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(check_formAbsence)
                .addContainerGap())
        );

        btn_displayCalendar.setText("Calendar");
        btn_displayCalendar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_displayCalendarMouseClicked(evt);
            }
        });
        btn_displayCalendar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_displayCalendarActionPerformed(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Goal Prompt Levels"));

        lbl_goalPromptLevels.setText("1=Independently    2=Verbal Prompt    3=Demonstration Prompt    4=Physical Assistance    5=Refused    6=Not Tracked");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lbl_goalPromptLevels)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lbl_goalPromptLevels)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        btn_newNote.setText("New Note");
        btn_newNote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_newNoteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_noteWritingLayout = new javax.swing.GroupLayout(panel_noteWriting);
        panel_noteWriting.setLayout(panel_noteWritingLayout);
        panel_noteWritingLayout.setHorizontalGroup(
            panel_noteWritingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_noteWritingLayout.createSequentialGroup()
                .addGroup(panel_noteWritingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel_noteWritingLayout.createSequentialGroup()
                        .addComponent(lbl_noteChooseParticipant)
                        .addGap(10, 10, 10)
                        .addComponent(combo_noteChooseParticipant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_displayCalendar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_saveNote)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_newNote)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_submitNote))
                    .addGroup(panel_noteWritingLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel_noteWritingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panel_noteWritingLayout.createSequentialGroup()
                                .addGap(0, 48, Short.MAX_VALUE)
                                .addComponent(panel_noteWritingGoals, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_noteWritingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_noteWritingLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_noteWritingLayout.createSequentialGroup()
                        .addComponent(lbl_todayDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34))))
        );
        panel_noteWritingLayout.setVerticalGroup(
            panel_noteWritingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_noteWritingLayout.createSequentialGroup()
                .addGroup(panel_noteWritingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_todayDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_noteWritingLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel_noteWritingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_saveNote)
                            .addComponent(combo_noteChooseParticipant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_noteChooseParticipant)
                            .addComponent(btn_displayCalendar)
                            .addComponent(btn_submitNote)
                            .addComponent(btn_newNote))))
                .addGap(18, 18, 18)
                .addGroup(panel_noteWritingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_noteWritingGoals, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        tabPane_container.addTab("Note Writing", panel_noteWriting);

        panel_updatePassword.setBorder(javax.swing.BorderFactory.createTitledBorder("Update Password"));

        lbl_enterOldPassword.setText("Old Password:");

        lbl_enterNewPassword.setText("New Password:");

        lbl_confirmNewPassword.setText("Confirm new password:");

        btn_cancelNewPass.setText("Cancel");
        btn_cancelNewPass.setToolTipText("Nevermind, keep your old password.");
        btn_cancelNewPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelNewPassActionPerformed(evt);
            }
        });

        btn_confirmNewPass.setText("Confirm");
        btn_confirmNewPass.setToolTipText("Accept your new password.");
        btn_confirmNewPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_confirmNewPassActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_updatePasswordLayout = new javax.swing.GroupLayout(panel_updatePassword);
        panel_updatePassword.setLayout(panel_updatePasswordLayout);
        panel_updatePasswordLayout.setHorizontalGroup(
            panel_updatePasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_updatePasswordLayout.createSequentialGroup()
                .addGroup(panel_updatePasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_updatePasswordLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(panel_updatePasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_confirmNewPassword)
                            .addComponent(lbl_enterNewPassword, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbl_enterOldPassword, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(panel_updatePasswordLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btn_cancelNewPass)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_updatePasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(pass_enterNewPass, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                    .addComponent(pass_confirmNewPass, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pass_enterOldPass)
                    .addComponent(btn_confirmNewPass))
                .addGap(0, 186, Short.MAX_VALUE))
        );

        panel_updatePasswordLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btn_cancelNewPass, btn_confirmNewPass});

        panel_updatePasswordLayout.setVerticalGroup(
            panel_updatePasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_updatePasswordLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(panel_updatePasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pass_enterOldPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_enterOldPassword))
                .addGap(51, 51, 51)
                .addGroup(panel_updatePasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_enterNewPassword)
                    .addComponent(pass_enterNewPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_updatePasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_confirmNewPassword)
                    .addComponent(pass_confirmNewPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(panel_updatePasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_cancelNewPass)
                    .addComponent(btn_confirmNewPass))
                .addContainerGap(326, Short.MAX_VALUE))
        );

        panel_mySavedNotes.setBorder(javax.swing.BorderFactory.createTitledBorder("My Notes"));

        combo_selectNoteToOpen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Saved Notes...", " ", " ", " ", " ", " ", " " }));
        combo_selectNoteToOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_selectNoteToOpenActionPerformed(evt);
            }
        });
        combo_selectNoteToOpen.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                combo_selectNoteToOpenFocusGained(evt);
            }
        });

        lbl_selectNoteToOpen.setText("Select a note to continue work on.");

        btn_openNote.setText("Open");
        btn_openNote.setToolTipText("Open the selected note in the Note Writing tab.");
        btn_openNote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_openNoteActionPerformed(evt);
            }
        });

        btn_deleteNote.setText("Delete");
        btn_deleteNote.setToolTipText("Delete the selected note.");
        btn_deleteNote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteNoteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_mySavedNotesLayout = new javax.swing.GroupLayout(panel_mySavedNotes);
        panel_mySavedNotes.setLayout(panel_mySavedNotesLayout);
        panel_mySavedNotesLayout.setHorizontalGroup(
            panel_mySavedNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_mySavedNotesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_mySavedNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(combo_selectNoteToOpen, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panel_mySavedNotesLayout.createSequentialGroup()
                        .addComponent(lbl_selectNoteToOpen)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_mySavedNotesLayout.createSequentialGroup()
                .addContainerGap(180, Short.MAX_VALUE)
                .addComponent(btn_openNote)
                .addGap(18, 18, 18)
                .addComponent(btn_deleteNote)
                .addGap(93, 93, 93))
        );

        panel_mySavedNotesLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btn_deleteNote, btn_openNote});

        panel_mySavedNotesLayout.setVerticalGroup(
            panel_mySavedNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_mySavedNotesLayout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(lbl_selectNoteToOpen)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(combo_selectNoteToOpen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_mySavedNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_deleteNote)
                    .addComponent(btn_openNote))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel_myAccountLayout = new javax.swing.GroupLayout(panel_myAccount);
        panel_myAccount.setLayout(panel_myAccountLayout);
        panel_myAccountLayout.setHorizontalGroup(
            panel_myAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_myAccountLayout.createSequentialGroup()
                .addComponent(panel_updatePassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_mySavedNotes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panel_myAccountLayout.setVerticalGroup(
            panel_myAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_myAccountLayout.createSequentialGroup()
                .addGroup(panel_myAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_mySavedNotes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_updatePassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        tabPane_container.addTab("My Account", panel_myAccount);

        panel_files.setEnabled(false);

        btn_uploadFile.setText("Upload");
        btn_uploadFile.setToolTipText("");
        btn_uploadFile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_uploadFileMouseClicked(evt);
            }
        });
        btn_uploadFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_uploadFileActionPerformed(evt);
            }
        });

        lbl_uploadFiles.setText("Choose a file to upload:");

        lbl_downloadFiles.setText("Choose a file to download:");

        btn_downloadFiles.setText("Download");
        btn_downloadFiles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_downloadFilesMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panel_fileManagingLayout = new javax.swing.GroupLayout(panel_fileManaging);
        panel_fileManaging.setLayout(panel_fileManagingLayout);
        panel_fileManagingLayout.setHorizontalGroup(
            panel_fileManagingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_fileManagingLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(panel_fileManagingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbl_downloadFiles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_uploadFiles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(panel_fileManagingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_fileManagingLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_uploadFile))
                    .addGroup(panel_fileManagingLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(btn_downloadFiles)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(26, 26, 26))
        );

        panel_fileManagingLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btn_downloadFiles, btn_uploadFile});

        panel_fileManagingLayout.setVerticalGroup(
            panel_fileManagingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_fileManagingLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(panel_fileManagingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_uploadFile)
                    .addComponent(lbl_uploadFiles))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(panel_fileManagingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_downloadFiles)
                    .addComponent(btn_downloadFiles))
                .addGap(31, 31, 31))
        );

        javax.swing.GroupLayout panel_filesLayout = new javax.swing.GroupLayout(panel_files);
        panel_files.setLayout(panel_filesLayout);
        panel_filesLayout.setHorizontalGroup(
            panel_filesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_filesLayout.createSequentialGroup()
                .addContainerGap(362, Short.MAX_VALUE)
                .addComponent(panel_fileManaging, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(213, 213, 213))
        );
        panel_filesLayout.setVerticalGroup(
            panel_filesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_filesLayout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(panel_fileManaging, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(363, Short.MAX_VALUE))
        );

        tabPane_container.addTab("Files", panel_files);

        list_timeReviewParticipant.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                list_timeReviewParticipantMouseClicked(evt);
            }
        });
        scroll_timeReviewParticipant.setViewportView(list_timeReviewParticipant);

        lbl_listOfTimes.setText("Participants:");

        txt_timeReviewNote.setColumns(20);
        txt_timeReviewNote.setLineWrap(true);
        txt_timeReviewNote.setRows(5);
        txt_timeReviewNote.setWrapStyleWord(true);
        scroll_timeReviewNote.setViewportView(txt_timeReviewNote);

        lbl_noteLabel.setText("Note:");

        btn_rejectTime.setText("Reject");
        btn_rejectTime.setToolTipText("Reject the note.");
        btn_rejectTime.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_rejectTimeMouseClicked(evt);
            }
        });

        btn_editTime.setText("Edit");
        btn_editTime.setToolTipText("Add the comments you've written below to the accepted or rejected note.");
        btn_editTime.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_editTimeMouseClicked(evt);
            }
        });

        btn_approveTime.setText("Approve");
        btn_approveTime.setToolTipText("Approve of the note.");

        javax.swing.GroupLayout panel_timeReviewLayout = new javax.swing.GroupLayout(panel_timeReview);
        panel_timeReview.setLayout(panel_timeReviewLayout);
        panel_timeReviewLayout.setHorizontalGroup(
            panel_timeReviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_timeReviewLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_timeReviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scroll_timeReviewParticipant, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_listOfTimes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(panel_timeReviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_timeReviewLayout.createSequentialGroup()
                        .addComponent(btn_rejectTime)
                        .addGap(24, 24, 24)
                        .addComponent(btn_editTime)
                        .addGap(18, 18, 18)
                        .addComponent(btn_approveTime))
                    .addComponent(scroll_timeReviewNote, javax.swing.GroupLayout.PREFERRED_SIZE, 648, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_noteLabel))
                .addContainerGap())
        );
        panel_timeReviewLayout.setVerticalGroup(
            panel_timeReviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_timeReviewLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel_timeReviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_listOfTimes)
                    .addComponent(lbl_noteLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_timeReviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(scroll_timeReviewNote)
                    .addComponent(scroll_timeReviewParticipant, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE))
                .addGap(29, 29, 29)
                .addGroup(panel_timeReviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_approveTime)
                    .addComponent(btn_rejectTime)
                    .addComponent(btn_editTime))
                .addGap(39, 39, 39))
        );

        tabPane_container.addTab("Time Review", panel_timeReview);

        lbl_StartDate.setText("From:");

        lbl_EndDate.setText("To:");

        btn_Print.setText("Print");
        btn_Print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_PrintActionPerformed(evt);
            }
        });

        lbl_location.setText("Choose Location:");

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Residential");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Day Center");

        lbl_MedicaidID.setText("Medicaid ID:");

        txt_MedicaidID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_MedicaidIDActionPerformed(evt);
            }
        });

        jLabel1.setText("Select Date Range:");

        txt_PatientName.setEditable(false);
        txt_PatientName.setBackground(new java.awt.Color(255, 255, 255));
        txt_PatientName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_PatientNameActionPerformed(evt);
            }
        });

        lbl_PatientName.setText("Patient:");

        btn_OK.setText("OK");
        btn_OK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_OKActionPerformed(evt);
            }
        });

        btn_Save.setText("Save");
        btn_Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lbl_MedicaidID)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(btn_OK)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(txt_MedicaidID, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbl_PatientName)))
                                .addGap(18, 18, 18)
                                .addComponent(txt_PatientName, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(327, 327, 327)
                                .addComponent(jLabel1))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jRadioButton1)
                        .addGap(49, 49, 49)
                        .addComponent(jRadioButton2))
                    .addComponent(lbl_location)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(388, 388, 388)
                                .addComponent(lbl_StartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbl_EndDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(btn_Print, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_Save, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_StartDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_EndDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap(297, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(lbl_location)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 34, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2))
                .addGap(46, 46, 46)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_MedicaidID, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_MedicaidID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_PatientName)
                    .addComponent(txt_PatientName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(btn_OK))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_StartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_StartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lbl_EndDate)
                        .addGap(32, 32, 32)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_Print)
                            .addComponent(btn_Save)))
                    .addComponent(txt_EndDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(265, 265, 265))
        );

        tabPane_container.addTab("Print", jPanel6);

        getContentPane().add(tabPane_container);
        //remove the non-functional "files" tab
        //delete the following line to reenable it once complete
        tabPane_container.remove(6);

        menu_file.setText("File");

        item_logout.setText("Logout");
        item_logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_logoutActionPerformed(evt);
            }
        });
        menu_file.add(item_logout);

        item_exit.setText("Exit");
        item_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_exitActionPerformed(evt);
            }
        });
        menu_file.add(item_exit);

        menuBar_adminForm.add(menu_file);

        menu_edit.setText("Edit");

        item_preferences.setText("Preferences");
        menu_edit.add(item_preferences);

        menuBar_adminForm.add(menu_edit);

        menu_help.setText("Help");

        item_documentation.setText("Documentation");
        item_documentation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_documentationActionPerformed(evt);
            }
        });
        menu_help.add(item_documentation);

        item_about.setText("About");
        item_about.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_aboutActionPerformed(evt);
            }
        });
        menu_help.add(item_about);

        menuBar_adminForm.add(menu_help);

        setJMenuBar(menuBar_adminForm);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Updates the form after an action is performed
     */
    private void updateForm() {
        try {
            populateParticipantNoteReviewJList(NoteLogic.populateNotesPendingReview());
            populateComboParticipant(ParticipantLogic.populate());
            populateParticipantJList(ParticipantLogic.populate());
        } catch (SQLException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Clears a note
     */
    private void clearNote() {
        combo_noteChooseParticipant.setSelectedIndex(0);
        combo_promptingLevel1.setSelectedIndex(0);
        combo_promptingLevel2.setSelectedIndex(0);
        combo_promptingLevel3.setSelectedIndex(0);
        combo_promptingLevel4.setSelectedIndex(0);
        combo_promptingLevel5.setSelectedIndex(0);
        radio_goal1Yes.setSelected(false);
        radio_goal1No.setSelected(false);
        radio_goal2Yes.setSelected(false);
        radio_goal2No.setSelected(false);
        radio_goal3Yes.setSelected(false);
        radio_goal3No.setSelected(false);
        radio_goal4Yes.setSelected(false);
        radio_goal4No.setSelected(false);
        radio_goal5Yes.setSelected(false);
        radio_goal5No.setSelected(false);
        btnGroup_yesNo1.clearSelection();
        btnGroup_yesNo2.clearSelection();
        btnGroup_yesNo3.clearSelection();
        btnGroup_yesNo4.clearSelection();
        btnGroup_yesNo5.clearSelection();
        txt_goalDescription1.setText("");
        txt_goalDescription2.setText("");
        txt_goalDescription3.setText("");
        txt_goalDescription4.setText("");
        txt_goalDescription5.setText("");
        txt_noteContent.setText("");
        check_formAbsence.setSelected(false);
        check_formBloodPressure.setSelected(false);
        check_formBodyCheck.setSelected(false);
        check_formBowelMovement.setSelected(false);
        check_formCCA.setSelected(false);
        check_formIncident.setSelected(false);
        check_formNone.setSelected(false);
        check_formReposition.setSelected(false);
        check_formSeizure.setSelected(false);
        txt_noteComment.setText("");
    }

    /**
     * clear User form fields
     */
    private void clearUsers() {
        combo_selectUser.setSelectedIndex(0);
        txt_userID.setText(null);
        txt_userPass.setText(null);
        txt_userFirstName.setText(null);
        txt_userLastName.setText(null);
	txt_userTitle.setText(null);
        combo_userLevel.setSelectedIndex(0);
    }

    /**
     * clear GoalLogic form fields
     */
    private void clearGoals() {
        combo_goalPartSelect.setSelectedIndex(0);
        combo_goalGoalSelect.setSelectedIndex(0);
        combo_frequency.setSelectedIndex(0);
        combo_interval.setSelectedIndex(0);
        txt_goalDescription.setText(null);
        txt_objectiveDescription.setText(null);
        txt_guidanceNotes.setText(null);
    }

    //clear Participant form fields
    private void clearParticipants() {
        combo_participantSelect.setSelectedIndex(0);
        check_medicaid.setSelected(false);
        check_selfPay.setSelected(false);
        txt_participantID.setText(null);
        txt_participantFirstName.setText(null);
        txt_participantLastName.setText(null);
        check_seizure.setSelected(false);
        check_bm.setSelected(false);
        check_repositioning.setSelected(false);
    }
    /**
     * 
     * @param evt 
     */
    private void btn_newUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_newUserMouseClicked
        //dialog_newUser.show();
    }//GEN-LAST:event_btn_newUserMouseClicked
    /**
     * 
     * @param evt 
     */
    private void btn_uploadFileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_uploadFileMouseClicked
        dialog_uploadFile.show();
    }//GEN-LAST:event_btn_uploadFileMouseClicked
    /**
     * 
     * @param evt 
     */
    private void btn_downloadFilesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_downloadFilesMouseClicked
        dialog_downloadFile.show();
    }//GEN-LAST:event_btn_downloadFilesMouseClicked
    /**
     * This method is used when the Note Reviewer must reject a note that is not
     * complete, it also allows them to leave comments on why it is rejected so
     * the DCP can fix the errors and then re-submit
     * @param evt 
     */
    private void btn_rejectNoteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_rejectNoteMouseClicked
        //Tyrus added an if statement to alert the user to select a note to 
        //reject if a note is not selected.
        //this line of code sets the minimum size of the comment dialog box so
        //the user does not have to manually change the size.
        dialog_addComments.setMinimumSize(new java.awt.Dimension(562,430));
        if (list_selectNoteToReview.getSelectedValue() != null) {
            dialog_addComments.show();
            //Steven added this
            txt_rejectNoteComment.setText(comment);
        } else {
            JOptionPane.showMessageDialog(null, "Select a note to reject.");
        }
    }//GEN-LAST:event_btn_rejectNoteMouseClicked
    /**
     * This method is used to add a new user into the DB
     *
     * @param evt
     */
    private void btn_newUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_newUserActionPerformed
       String selectedItem = (String) combo_userLevel.getSelectedItem();
		//Ty added an if statement to check if the user has a selected clearance and if 
        //not it propmts the user to select one for the user.
        if ("Select A User Level".equals(selectedItem)) {
            JOptionPane.showMessageDialog(null, "Please select a clearance level for the user!");
        }else if (!"Select A User Level".equals(selectedItem)) {
            int selectedOption = JOptionPane.showConfirmDialog(null,
                "Would you like to create this user?",
                "New user?",
                JOptionPane.YES_NO_OPTION);
        if (selectedOption == JOptionPane.YES_OPTION) {
            try {

                UserLogic ul = new UserLogic();
                UserDBConnect uc = new UserDBConnect();
                if (UserDBConnect.doesExist(txt_userID.getText()) == false) {
                    ul.insertUser(txt_userID.getText(), txt_userPass.getText(), txt_userFirstName.getText(), txt_userLastName.getText(), txt_userTitle.getText(), combo_userLevel.getSelectedItem());
                    populateComboSelectUser(UserDBConnect.populateUser());
                } else if (UserDBConnect.doesExist(txt_userID.getText()) == true) {
                    JOptionPane.showMessageDialog(null, "Username already exists! Please select a unique Username.");
                }
            } catch (SQLException ex) {
                Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
		}
    }//GEN-LAST:event_btn_newUserActionPerformed
    /**
     * This method is used to remove an employee or auditor from the DB
     *
     * @param evt
     */
    private void btn_deleteUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteUserActionPerformed
        int selectedOption = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to delete this user?",
                "Delete user?",
                JOptionPane.YES_NO_OPTION);
        if (selectedOption == JOptionPane.YES_OPTION) {
            try {
                UserLogic ul = new UserLogic();

                ul.uc.deleteUser(txt_userID.getText());
                populateComboSelectUser(ul.uc.populateUser());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btn_deleteUserActionPerformed
    /**
     *
     * @param evt
     */
    private void combo_selectUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_selectUserActionPerformed
                                             
        try {

            UserLogic ul = new UserLogic();
            ul.select(combo_selectUser.getSelectedItem());
            String[] array = {ul.getUsername(), ul.getPassword(), ul.getFirstName(), ul.getLastName(), ul.getUserTitle(), ul.clearanceCheck(ul.getClearance())};
            System.out.println("User selected: " + (String) combo_selectUser.getSelectedItem());

            if (array != null && combo_selectUser.getSelectedIndex() != 0) {
                txt_userID.setText(array[0]);
                txt_userPass.setText(array[1]);
                txt_userFirstName.setText(array[2]);
                txt_userLastName.setText(array[3]);
                txt_userTitle.setText(array[4]);
                combo_userLevel.setSelectedItem(array[5]);
            }
            if (combo_selectUser.getSelectedIndex() == 0) {
                clearUsers();
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.err.println("UserSelect in the class UserDBConnect generates the out of bounds exception when the program is initially run. \n"
                    + "The error also occurs when the combo box has the default option selected \n"
                    + "The error is NOT critical and does not interfere with the program's intented functions \n"
                    + "Since it is negligible it CAN be ignored, but it should be rectified anyway. -Triston Gregoire");
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_combo_selectUserActionPerformed
    /**
     * This method is used to update the necessary fields that have been
     * modified for a participant
     *
     * @param evt
     */
    private void btn_updateUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateUserActionPerformed
        int selectedOption = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to update this user?",
                "Update user?",
                JOptionPane.YES_NO_OPTION);
        if (selectedOption == JOptionPane.YES_OPTION) {
            try {
                UserLogic ul = new UserLogic();
                ul.setUsername(txt_userID.getText());
                ul.setPassword(txt_userPass.getText());
                ul.setFirstName(txt_userFirstName.getText());
                ul.setLastName(txt_userLastName.getText());
		ul.setUserTitle(txt_userTitle.getText());
                ul.setClearance(ul.clearanceCheck((String) combo_userLevel.getSelectedItem()));
                ul.setOldUserName((String) combo_selectUser.getSelectedItem());
                ul.updateUser();

                populateComboSelectUser(UserDBConnect.populateUser());
            } catch (SQLException ex) {
                Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btn_updateUserActionPerformed
    /**
     *
     * @param evt
     */
    private void btn_viewUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_viewUserActionPerformed
        txt_userID.setText("");
        txt_userPass.setText("");
        txt_userFirstName.setText("");
        txt_userLastName.setText("");
	txt_userTitle.setText("");
        combo_userLevel.setSelectedIndex(0);
        combo_selectUser.setSelectedIndex(0);
    }//GEN-LAST:event_btn_viewUserActionPerformed

    /**
     * This method is used to update a password for a user
     *
     * @param evt
     */
    private void btn_confirmNewPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_confirmNewPassActionPerformed
        try {
            AccountLogic acc = new AccountLogic();
            char[] oldPassword = pass_enterOldPass.getPassword();
            char[] newPassword = pass_enterNewPass.getPassword();
            char[] newPasswordCompare = pass_confirmNewPass.getPassword();
            boolean newPassMatch = Arrays.equals(newPassword, newPasswordCompare);

            System.err.println("rgeqaqfet4etraertegregrw" + acc.validate(activeUser, oldPassword));
            if (acc.validate(activeUser, oldPassword) && Arrays.equals(newPassword, newPasswordCompare)) {

                int selectedOption = JOptionPane.showConfirmDialog(null,
                        "Are you sure you would like to update"
                        + " your password?",
                        "Update password?",
                        JOptionPane.YES_NO_OPTION);
                if (selectedOption == JOptionPane.YES_OPTION) {
                    try {
                        AccountLogic ac = new AccountLogic();
                        //        ac.setOldPassword(pass_enterOldPass.getPassword());
                        //        ac.setNewPassword(pass_enterNewPass.getPassword());
                        ac.updateAccount(pass_enterNewPass.getPassword());
                        pass_confirmNewPass.getPassword();
                        JOptionPane.showMessageDialog(null, "Your password is now updated");
                    } catch (ClassNotFoundException | SQLException ex) {
                        Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else if (!acc.validate(activeUser, oldPassword)) {
                JOptionPane.showMessageDialog(null, "Old password is incorrect!");
            } else if (!newPassMatch) {
                JOptionPane.showMessageDialog(null, "New Passwords do not match!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Old password is incorrect!");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_btn_confirmNewPassActionPerformed
    /**
     * This method is called when the user is ready to submit a note to the user
     * that reviews the notes
     * @param evt
     */
    private void btn_submitNoteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_submitNoteMouseClicked

        // --------Once the radio buttons actually do something, a check similar to
        // the following should be performed. Ideally, if the participant does not have 5 goals,
        // it should only perform a check on how many goals they *do* have.
        //----------------------------------------------------------------------
//        if ((!radio_goal1No.isSelected() && !radio_goal1Yes.isSelected()) ||
//                (!radio_goal2No.isSelected() && !radio_goal2Yes.isSelected()) ||
//                (!radio_goal3No.isSelected() && !radio_goal3Yes.isSelected()) ||
//                (!radio_goal4No.isSelected() && !radio_goal4Yes.isSelected()) ||
//                (!radio_goal5No.isSelected() && !radio_goal5Yes.isSelected())){
//            JOptionPane.showMessageDialog(null, "Please indicate whether or not each goal "
//                    + "was achieved on this day.");
//        }
//        else {
        if (combo_noteChooseParticipant.getSelectedIndex() != 0) {
            if (openDocTest) {
                System.out.println(noteID + " reject test in ");
                NoteLogic note;
                try {
                    note = NoteLogic.select(noteID);
                    comment = note.getComment();
                    System.out.println(comment);
                    txt_noteComment.setText(comment);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            dialog_submitNote.show();

        } else {
            JOptionPane.showMessageDialog(null, "Please select a participant.");
        }
        //}
    }//GEN-LAST:event_btn_submitNoteMouseClicked
    /**
     * 
     * @param evt
     */
    private void check_medicaidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_medicaidActionPerformed

        check_selfPay.setSelected(false);
        if (!txt_participantID.isEnabled()) {
            txt_participantID.setEnabled(true);
        }
        System.out.println(check_medicaid.getText());
    }//GEN-LAST:event_check_medicaidActionPerformed
    /**
     *
     * @param evt
     */
    private void check_selfPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_selfPayActionPerformed
        check_medicaid.setSelected(false);
        txt_participantID.setText(null);
        txt_participantID.setEnabled(false);
        System.out.println(check_selfPay.getText());
    }//GEN-LAST:event_check_selfPayActionPerformed
    /**
     * This method is used to add a new participant into the DB
     *
     * @param evt
     */
    private void btn_addParticipantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addParticipantActionPerformed

        int selectedOption = JOptionPane.showConfirmDialog(null,
                "Are you sure you would like to add"
                + " this participant?",
                "Add Participant?",
                JOptionPane.YES_NO_OPTION);
        if (selectedOption == JOptionPane.YES_OPTION) {
            if (combo_participantSelect.getSelectedIndex() == 0) {
                ParticipantLogic pl = new ParticipantLogic();
                try {
                    if (txt_participantID.isEnabled()) {
                        pl.setMedicaidNumber(txt_participantID.getText());
                        System.out.println(txt_participantID.isEnabled());
                    }
                    pl.setParticipantFirstName(txt_participantFirstName.getText());
                    pl.setParticipantLastName(txt_participantLastName.getText());
                    if (!check_selfPay.isSelected() && !check_medicaid.isSelected()) {
                        showMessageDialog(null, "Please select the Insurance type!");
                        System.out.println("None selected!");
                    } else if (check_selfPay.isSelected()) {
                        pl.setInsurance(check_selfPay.getText());
                        System.out.println("SelfPay!");
                    } else if (check_medicaid.isSelected()) {
                        pl.setInsurance(check_medicaid.getText());
                        System.out.println("Medicaid!");
                    }
                    pl.addParticipant();
                    populateComboParticipant(ParticipantLogic.populate());
                    populateParticipantJList(ParticipantLogic.populate());
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                showMessageDialog(null, "You have successfully added this participant.");
            } else {
                showMessageDialog(null, "Please choose \"Select Participant Or Create New\" from "
                        + "the dropdown menu to add a participant.");
            }
        }
    }//GEN-LAST:event_btn_addParticipantActionPerformed
    /**
     * This method is used to pull all the information for a participant from
     * the DB and populate all the necessary fields under the "Participants" tab
     *
     * @param evt
     */
    private void combo_participantSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_participantSelectActionPerformed
        System.out.println("Participant" + combo_participantSelect.getSelectedItem());
        ParticipantLogic part = new ParticipantLogic();
        Object participant = combo_participantSelect.getSelectedItem();
        if (combo_participantSelect.getSelectedIndex() == 0) {
            clearParticipants();
        }

        if (participant != null && !participant.equals("Select A Participant")) {
            try {
                String[] partArray = new String[2];
                String selectedParticipant = (String) participant;
                partArray = selectedParticipant.split(":");
                int ID = Integer.parseInt(partArray[0]);
                System.out.println(partArray[0]);
                part = part.select(ID);
                txt_participantFirstName.setText(part.getParticipantFirstName());
                txt_participantLastName.setText(part.getParticipantLastName());
                switch (part.getInsurance()) {
                    case "Medicaid":
                        check_selfPay.setSelected(false);
                        check_medicaid.setSelected(true);
                        txt_participantID.setEnabled(true);
                        txt_participantID.setText(part.getMedicaidNumber());
                        break;
                    case "Self-Pay":
                        check_selfPay.setSelected(true);
                        check_medicaid.setSelected(false);
                        txt_participantID.setText(null);
                        txt_participantID.setEnabled(false);
                        break;
                }
                pl = part;
            } catch (SQLException ex) {
                Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {

            pl = null;  //Create dialog box for exception this line creates prompting user to select a Participant before clicking update

        }

    }//GEN-LAST:event_combo_participantSelectActionPerformed
    /**
     * This method is used to pull all the goals that are assigned to a
     * participant into the goal dropdown box under the "Goals" tab
     *
     * @param evt
     */
    private void combo_goalPartSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_goalPartSelectActionPerformed
        try {
            ParticipantLogic participant = new ParticipantLogic();
            if (!"Select A Participant".equals(combo_goalPartSelect.getSelectedItem()));
            Object input = combo_goalPartSelect.getSelectedItem();
            ArrayList<GoalLogic> goalList;
            goalList = participant.gl.selectGoalsOrSomeShit(input);
            populateComboGoal(goalList);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_combo_goalPartSelectActionPerformed

    private void btn_rejectTimeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_rejectTimeMouseClicked
        dialog_rejectTime.show();
    }//GEN-LAST:event_btn_rejectTimeMouseClicked
    /**
     * This method is used to update the information for the participant and
     * update it in the DB
     *
     * @param evt
     */
    private void btn_updateParticipantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateParticipantActionPerformed
        int selectedOption = JOptionPane.showConfirmDialog(null,
                "Would you like to update the "
                + "participant's information?",
                "Update participant?",
                JOptionPane.YES_NO_OPTION);
        if (selectedOption == JOptionPane.YES_OPTION) {
            try {
                String insurance = null;
                if (check_medicaid.isSelected()) {
                    insurance = check_medicaid.getText();
                } else if (check_selfPay.isSelected()) {
                    insurance = check_selfPay.getText();
                } else if (!check_medicaid.isSelected() && !check_selfPay.isSelected()) {
                    System.out.println("Insurance type must be selected!");//Make Dialog or some sort of feedback for this.
                    showMessageDialog(null, "Please make sure that you have selected an "
                            + "insurance type to update.");
                }

                String placeholder = (String) combo_participantSelect.getSelectedItem();
                int id = Integer.parseInt(placeholder.split(":")[0]);
                pl.updateParticipant(txt_participantID.getText(), txt_participantFirstName.getText(), txt_participantLastName.getText(),
                        insurance, id);
                System.out.println(pl.getParticipantFirstName());
                populateComboParticipant(ParticipantLogic.populate());
                populateParticipantJList(ParticipantLogic.populate());
            } catch (SQLException ex) {
                Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            showMessageDialog(null, "You have successfully updated this participant.");
        }
    }//GEN-LAST:event_btn_updateParticipantActionPerformed
    /**
     * This method is used when the user clicks the Calendar button in the Note
     * Writing tab, at this moment it really does not have any type of functionality
     * the group that is assigned to this project after Ctrl/Alt/Delicious needs to
     * work on this.
     * @param evt 
     */
    private void btn_displayCalendarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_displayCalendarMouseClicked
//        UI_Calendar cal = new UI_Calendar();
//        cal.setVisible(true);
        UI_Calendar.main(new String[]{});
    }//GEN-LAST:event_btn_displayCalendarMouseClicked
    /**
     * This method populates the necessary information for a selected goal into
     * the assigned Text Fields.
     *
     * @param evt
     */
    private void combo_goalGoalSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_goalGoalSelectActionPerformed
        try {
            ParticipantLogic pl = new ParticipantLogic();
            ArrayList<GoalLogic> goalList = pl.gl.selectGoalsOrSomeShit(combo_goalPartSelect.getSelectedItem());
            System.out.println("Index: " + combo_goalGoalSelect.getSelectedIndex());
            String placeholder = (String) combo_goalGoalSelect.getSelectedItem();
            System.out.println(placeholder);
            if (placeholder != null && placeholder.contains("-")) {
                String[] str = placeholder.split("-");
                int goalNum = Integer.parseInt(str[1]);
                GoalLogic selectedGoal = goalList.get(goalNum - 1);
                txt_goalDescription.setText(selectedGoal.getDescription());
                txt_objectiveDescription.setText(selectedGoal.getObjective());
                txt_guidanceNotes.setText(selectedGoal.getGuidanceNote());
                combo_frequency.setSelectedIndex(selectedGoal.getFrequency());

                if (selectedGoal.getIsWeekly() == 1) {
                    combo_interval.setSelectedItem("Week");
                } else if (selectedGoal.getIsWeekly() == 0) {
                    combo_interval.setSelectedItem("Month");
                }
            } else if (placeholder != null && placeholder.contains("_")) {
                txt_goalDescription.setText(null);
                txt_objectiveDescription.setText(null);
                txt_guidanceNotes.setText(null);
                combo_frequency.setSelectedIndex(0);
                combo_interval.setSelectedIndex(0);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_combo_goalGoalSelectActionPerformed
    /**
     * This method is used to add a goal for a participant and store it in the
     * DB
     *
     * @param evt
     */
    private void btn_goalAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_goalAddActionPerformed
        int selectedOption = JOptionPane.showConfirmDialog(null,
                "Do you want to add this goal to "
                + "the selected participant?",
                "Add goal?",
                JOptionPane.YES_NO_OPTION);
        if (selectedOption == JOptionPane.YES_OPTION) {
            try {
                ParticipantLogic pl = new ParticipantLogic();
                pl.gl.add(combo_frequency.getSelectedItem(), combo_interval.getSelectedItem(),
                        txt_goalDescription.getText(), txt_objectiveDescription.getText(),
                        txt_guidanceNotes.getText(), combo_goalPartSelect.getSelectedItem());
                txt_goalDescription.setText("");
                txt_objectiveDescription.setText("");
                txt_guidanceNotes.setText("");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

//        try {
//            ParticipantLogic pl = new ParticipantLogic();
//            pl.gl.add(combo_frequency.getSelectedItem(), combo_interval.getSelectedItem(),
//                    txt_goalDescription.getText(), txt_objectiveDescription.getText(),        
//                    txt_guidanceNotes.getText(), combo_goalPartSelect.getSelectedItem());
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_btn_goalAddActionPerformed
    /**
     * 
     * @param evt 
     */
    private void combo_intervalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_intervalActionPerformed
        // TODO submit your handling code here:
    }//GEN-LAST:event_combo_intervalActionPerformed
    /**
     * This method is used to update the goal for a selected participant in the
     * DB
     *
     * @param evt
     */
    private void btn_goalUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_goalUpdateActionPerformed
        int selectedOption = JOptionPane.showConfirmDialog(null,
                "Do you wanna update this goal?",
                "Update?",
                JOptionPane.YES_NO_OPTION);
        if (selectedOption == JOptionPane.YES_OPTION) {
            try {
                ParticipantLogic pl = new ParticipantLogic();
                int goalNum = combo_goalGoalSelect.getSelectedIndex();
                goalNum = goalNum -= 1;
                ArrayList<GoalLogic> goalList = pl.gl.selectGoalsOrSomeShit(combo_goalPartSelect.getSelectedItem());
                pl.gl.update(combo_frequency.getSelectedItem(), combo_interval.getSelectedItem(),
                        txt_goalDescription.getText(), txt_objectiveDescription.getText(),
                        txt_guidanceNotes.getText(), goalList.get(goalNum).getGoalID());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btn_goalUpdateActionPerformed
    /**
     * This method is used to determine how many goals are needed to be shown
     * based on a participant
     *
     * @param evt
     */
    private void combo_noteChooseParticipantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_noteChooseParticipantActionPerformed
        try {
            GoalLogic gl = new GoalLogic();
            ArrayList<GoalLogic> goalList = new ArrayList();
            Object participant = combo_noteChooseParticipant.getSelectedItem();
            System.out.println(participant);
            if (participant != null && !"Select A Participant".equals(participant)) {
                goalList = gl.selectGoalsOrSomeShit(participant);

                switch (goalList.size()) {
                    case 0:
                        //Add nothing
                        //Clear Everything
                        txt_goalDescription1.setText("");
                        txt_goalDescription2.setText("");
                        txt_goalDescription3.setText("");
                        txt_goalDescription4.setText("");
                        txt_goalDescription5.setText("");
                        break;
                    case 1:
                        txt_goalDescription1.setText(goalList.get(0).getDescription());
                        break;
                    case 2:
                        txt_goalDescription1.setText(goalList.get(0).getDescription());
                        txt_goalDescription2.setText(goalList.get(1).getDescription());
                        break;
                    case 3:
                        txt_goalDescription1.setText(goalList.get(0).getDescription());
                        txt_goalDescription2.setText(goalList.get(1).getDescription());
                        txt_goalDescription3.setText(goalList.get(2).getDescription());
                        break;
                    case 4:
                        txt_goalDescription1.setText(goalList.get(0).getDescription());
                        txt_goalDescription2.setText(goalList.get(1).getDescription());
                        txt_goalDescription3.setText(goalList.get(2).getDescription());
                        txt_goalDescription4.setText(goalList.get(3).getDescription());
                        break;
                    case 5:
                        txt_goalDescription1.setText(goalList.get(0).getDescription());
                        txt_goalDescription2.setText(goalList.get(1).getDescription());
                        txt_goalDescription3.setText(goalList.get(2).getDescription());
                        txt_goalDescription4.setText(goalList.get(3).getDescription());
                        txt_goalDescription5.setText(goalList.get(4).getDescription());
                        break;
                }
            } else if ("Select A Participant".equals(participant)) {
                txt_goalDescription1.setText("");
                txt_goalDescription2.setText("");
                txt_goalDescription3.setText("");
                txt_goalDescription4.setText("");
                txt_goalDescription5.setText("");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IndexOutOfBoundsException ex) {
            System.err.println("Error occurs if the Participant selected has less than 5 Goals.\n"
                    + "This exception isn't fatal and doesn't need to be fixed, but the "
                    + "stacktrace is printed anyway in case you want to fix it -Triston Gregoire");
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_combo_noteChooseParticipantActionPerformed
    /**
     * 
     * @param evt 
     */
    private void btn_appendGoal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_appendGoal1ActionPerformed
        String goal1 = txt_goalDescription1.getText();
        if (!goal1.isEmpty()) {
            txt_noteContent.append(" " + goal1);
        }
    }//GEN-LAST:event_btn_appendGoal1ActionPerformed
    /**
     * 
     * @param evt 
     */
    private void btn_appendGoal2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_appendGoal2ActionPerformed
        String goal2 = txt_goalDescription2.getText();
        if (!goal2.isEmpty()) {
            txt_noteContent.append(" " + goal2);
        }
    }//GEN-LAST:event_btn_appendGoal2ActionPerformed
    /**
     * 
     * @param evt 
     */
    private void btn_appendGoal3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_appendGoal3ActionPerformed
        String goal3 = txt_goalDescription3.getText();
        if (!goal3.isEmpty()) {
            txt_noteContent.append(" " + goal3);
        }
    }//GEN-LAST:event_btn_appendGoal3ActionPerformed
    /**
     * 
     * @param evt 
     */
    private void btn_appendGoal4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_appendGoal4ActionPerformed
        String goal4 = txt_goalDescription4.getText();
        if (!goal4.isEmpty()) {
            txt_noteContent.append(" " + goal4);
        }
    }//GEN-LAST:event_btn_appendGoal4ActionPerformed
    /**
     * 
     * @param evt 
     */
    private void btn_appendGoal5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_appendGoal5ActionPerformed
        String goal5 = txt_goalDescription5.getText();
        if (!goal5.isEmpty()) {
            txt_noteContent.append(" " + goal5);
            System.out.println("desctiption:" + txt_goalDescription5.getText());
        }
    }//GEN-LAST:event_btn_appendGoal5ActionPerformed
    /**
     * 
     * @param evt 
     */
    private void btn_submitNoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_submitNoteActionPerformed
    }//GEN-LAST:event_btn_submitNoteActionPerformed

    /**
     * Method is used to save a note as a draft for the DCP to go back to and
     * finish later.
     *
     * @param evt
     */
    private void btn_saveNoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveNoteActionPerformed
        if (combo_noteChooseParticipant.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Please select a participant.");
        } else if (!openDocTest) {

            int selectedOption = JOptionPane.showConfirmDialog(null,
                    "Do you want to save the note as a draft?",
                    "Save note?",
                    JOptionPane.YES_NO_OPTION);
            if (selectedOption == JOptionPane.YES_OPTION) {
                try {
                    NoteLogic note = new NoteLogic();
                    note.submit(txt_noteContent.getText(), null, combo_noteChooseParticipant.getSelectedItem(), activeUser, 0);
                    populateSavedNoteList(NoteDBConnect.getSavedDraft(activeUser));
                    System.out.println("Note saved, apparently");
                    noteID = note.getNoteID();
                    System.out.println("Your saved noteID is: " + noteID);
                    openDocTest = true;
                    //clearNote();
                } catch (SQLException ex) {
                    Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NumberFormatException ex) {

                    Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            System.out.println("you didn't save but made it here");
            System.out.println(noteID);
            int selectedOption = JOptionPane.showConfirmDialog(null,
                    "Do you want to over write the note as a draft?",
                    "Overwrite Saved Note?",
                    JOptionPane.YES_NO_OPTION);
            if (selectedOption == JOptionPane.YES_OPTION) {
                System.out.println("Still not saved but closer");

                NoteDBConnect noteUpdate = new NoteDBConnect();
                try {
                    noteUpdate.upDate(noteID, txt_noteContent.getText());
                    //clearNote();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }//GEN-LAST:event_btn_saveNoteActionPerformed
    /**
     * This method is used to populate a saved note from the DB to the 
     * NoteWriting tab
     *
     * @param list
     */
    private void populateSavedNoteList(List<NoteLogic> list) {
        String notelist;
        combo_selectNoteToOpen.removeAllItems();
        combo_selectNoteToOpen.addItem("Saved Notes...");
        for (int i = 0; i < list.size(); i++) {
            int thisIdInt = list.get(i).getNoteID();
            Timestamp thisTimestamp = list.get(i).getTimeSubmitted();
            String thisId = Integer.toString(thisIdInt);
            String partName = list.get(i).getPl().getParticipantFirstName() + " "
                    + list.get(i).getPl().getParticipantLastName();
            //String timestamp = thisTimestamp.toString();
            String timestamp = new SimpleDateFormat("MM/dd/yyyy - h:mm:ss a").format(thisTimestamp);

            // Manual way to handle AM/PM
//            String substr = timestamp.substring(timestamp.length() - 8);
//            substr = substr.replaceAll("[:]", "");
//            String twelveHour = substr;
//            String time = null;
//            if((Integer.parseInt(twelveHour) < 120000) && (Integer.parseInt(twelveHour) >= 000000)){
//                time =  new StringBuilder(twelveHour).insert(twelveHour.length()-2, ":").toString();
//                time =  new StringBuilder(time).insert(time.length()-5, ":").toString() + "";
//                time = time + " AM";
//                timestamp =new SimpleDateFormat("MM/dd/yyyy").format(thisTimestamp);
//            }
//            else if((Integer.parseInt(twelveHour) < 240000) && (Integer.parseInt(twelveHour) >= 120000)){
//                time =  new StringBuilder(twelveHour).insert(twelveHour.length()-2, ":").toString();
//                time =  new StringBuilder(time).insert(time.length()-5, ":").toString() + "";
//                time = time + " PM";
//                timestamp =new SimpleDateFormat("MM/dd/yyyy").format(thisTimestamp);
//            }
//            //else(Integer.parseInt(twelveHour) => 120000){}
            combo_selectNoteToOpen.addItem(thisId + ": " + partName + ": " + timestamp);
        }
    }

    /**
     * Method used to submit a note from the DCP to the Note Reviewer
     *
     * @param evt
     */
    private void btn_submitSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_submitSubmitActionPerformed
        if (!openDocTest) {
            try {
                // TODO submit your handling code here:
                NoteLogic note = new NoteLogic();
                UserLogic user = new UserLogic();
                Object tempUser = (String) activeUser;
                List<NoteLogic> list1 = NoteDBConnect.selectByUser(user.select(tempUser).getUserID());
                note.submit(txt_noteContent.getText(), txt_noteComment.getText(), combo_noteChooseParticipant.getSelectedItem(), activeUser, 1);
                List<NoteLogic> list2 = NoteDBConnect.selectByUser(user.select(tempUser).getUserID());
                System.err.println("--------- " + tempUser + "---------");

                if ((list2.size() == 1 || !list1.get(0).getTimeSubmitted().after(list2.get(0).getTimeSubmitted()))) {
                    if (list2.size() > 0) {
                        updateForm();
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("your here at submit test!!!");
            System.out.println("your note id: " + noteID);
            System.out.println(txt_noteComment.getText());

            NoteDBConnect submitUpdate = new NoteDBConnect();

            try {
                submitUpdate.upDate(noteID, txt_noteContent.getText());
                submitUpdate.submitUpDate(noteID, txt_noteComment.getText(), 1);

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        newNote();
        dialog_submitNote.dispose();
        JOptionPane.showMessageDialog(null, "Your note was submitted.");
    }//GEN-LAST:event_btn_submitSubmitActionPerformed
    /**
     * 
     * @param evt 
     */
    private void list_timeReviewParticipantMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_list_timeReviewParticipantMouseClicked
        NoteLogic note = new NoteLogic();

        list_timeReviewParticipant.getSelectedIndex();
    }//GEN-LAST:event_list_timeReviewParticipantMouseClicked
    /**
     * This method's purpose is to populate the text field that is in the Note Review 
     * tab when the Note Reviewer selects one of the notes that needs to be reviewed 
     * that is populated in the top text field that is on the left.
     * @param evt
     */
    private void list_selectNoteToReviewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_list_selectNoteToReviewMouseClicked
        try {
            // TODO add your handling code here:
            //System.out.println(list_selectNoteToReview);
            System.err.println(list_selectNoteToReview.getSelectedValue());
            //System.out.println(list_selectNoteToReview.getSelectedValue().split("||")[0]);
            String selectedNoteID = list_selectNoteToReview.getSelectedValue();
            System.out.println("AdminForm.java " + selectedNoteID);
            //list_selectNoteToReview.getSe
            //System.err.println(selectedNoteID);
            String[] splitStr = selectedNoteID.trim().split("-");
            selectedNoteID = splitStr[0];
            System.err.println(Arrays.toString(splitStr));
            System.err.println(selectedNoteID);
            System.out.println("my check" + selectedNoteID);
            if (!"NoteID".equals(selectedNoteID)) {

                NoteLogic note = NoteLogic.select(Integer.parseInt(selectedNoteID.trim()));
                txt_noteToBeReviewed.setText(note.getText());

                //Steven Alcorn Changes to the code
                comment = note.getComment();
                System.out.println(comment);
                noteID = note.getNoteID();
                System.out.println(noteID);
                //end of my changes(this line not in mine just trying to make it easy for you.)
//                if (!note.equals(null)){
//                    
//                }
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {

        } catch (NumberFormatException ex) {
            System.err.println("The first item in the list_selectNoteToReview JList was clicked.\n "
                    + "Checking the String before calling Integer.parseInt would solve the issue but I really don't have time for this crap");
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_list_selectNoteToReviewMouseClicked
    /**
     * The purpose of this method is to allow the Note Reviewer to click this
     * button and have the notes that are to be reviewed shown in order by the name
     * of the participant.
     * @param evt 
     */
    private void btn_sortByParticipantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sortByParticipantActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_sortByParticipantActionPerformed
    /**
     * 
     * @param evt 
     */
    private void tabPane_containerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabPane_containerMouseClicked
//        try {
//            populateParticipantNoteReviewJList(NoteLogic.populateNotesPendingReview());
//            populateComboParticipant(ParticipantLogic.populate());
//            populateParticipantJList(ParticipantLogic.populate());
//        } catch (SQLException ex) {
//            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_tabPane_containerMouseClicked

    /**
     * Method used to Approve a note that has been submitted by the DCP to the
     * Note Reviewer.
     *
     * @param evt
     */
    private void btn_approveNoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_approveNoteActionPerformed
        //Ty added an if statement to alert the user to select a note to approve
        //if a note is not selected.
        if (list_selectNoteToReview.getSelectedValue() != null) {
            int selectedOption = JOptionPane.showConfirmDialog(null,
                    "Do you want to approve the note submission?",
                    "Approve note",
                    JOptionPane.YES_NO_OPTION);
            if (selectedOption == JOptionPane.YES_OPTION) {
                try {
                    NoteLogic note = new NoteLogic();
                    note.acceptNote(list_selectNoteToReview.getSelectedValue(), txt_noteToBeReviewed.getText(), 1);
                    populateParticipantNoteReviewJList(NoteLogic.populateNotesPendingReview());
                    txt_noteToBeReviewed.setText("");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a note to accept.");
        }

    }//GEN-LAST:event_btn_approveNoteActionPerformed
    /**
     * This closes the dialog box after the Note Reviewer has clicked the Reject
     * button.
     * @param evt 
     */
    private void btn_cancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cancelMouseClicked
        dialog_editNote.dispose();
    }//GEN-LAST:event_btn_cancelMouseClicked
    /**
     * I would assume that this closes the dialog box when the Time Reviewer
     * hits the Edit button. T
     * @param evt 
     */
    private void btn_cancelTimeChangeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cancelTimeChangeMouseClicked
        dialog_editTime.dispose();
    }//GEN-LAST:event_btn_cancelTimeChangeMouseClicked
    /**
     * This closes the dialog box that appears when the Time Reviewer clicks the
     * Reject button. 
     * @param evt 
     */
    private void btn_cancelRejectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cancelRejectMouseClicked
        dialog_rejectTime.dispose();
    }//GEN-LAST:event_btn_cancelRejectMouseClicked
    /**
     * This closes the comment dialog box that appears when a Note Reviewer 
     * rejects a note.
     * @param evt 
     */
    private void btn_cancelCommentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cancelCommentsMouseClicked
        dialog_addComments.dispose();
    }//GEN-LAST:event_btn_cancelCommentsMouseClicked
    /**
     * This closes the dialog box when the DCP decides to not submit the note yet
     * @param evt 
     */
    private void btn_submitCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_submitCancelMouseClicked
        dialog_submitNote.dispose();
    }//GEN-LAST:event_btn_submitCancelMouseClicked
    /**
     * This displays a dialog box when the Time Reviewer click the edit button
     * in the Time Review tab which allows them to add comments on why the time 
     * was edited.
     * @param evt 
     */
    private void btn_editTimeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_editTimeMouseClicked
        dialog_editTime.show();
    }//GEN-LAST:event_btn_editTimeMouseClicked
    /**
     * This displays a dialog box when the DCP clicks the submit button
     * in the Note Writing tab which allows them to add comments about the note
     * @param evt 
     */
    private void btn_submitSubmitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_submitSubmitMouseClicked
        dialog_submitNote.dispose();
    }//GEN-LAST:event_btn_submitSubmitMouseClicked
    /**
     * This allows the DCP to finally submit a note the Note Reviewer after they
     * decided to leave any comments or not.
     * @param evt 
     */
    private void btn_acceptAddCommentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_acceptAddCommentsMouseClicked
        //Steven Alcorn change to reject button
        System.out.println("AdminForm.java accepted reject you made it here.");
        System.out.println(txt_rejectNoteComment.getText());
        System.out.println(noteID);

        NoteDBConnect submitUpdate = new NoteDBConnect();

        try {
            submitUpdate.submitUpDate(noteID, txt_rejectNoteComment.getText(), 0);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        //end of my code (agian this is not in mine just trying to make it easy.)
        dialog_addComments.dispose();
    }//GEN-LAST:event_btn_acceptAddCommentsMouseClicked
    /**
     * Closes the rejectTime dialog box that appears when the Time Reviewer rejects a Time 
     * that is submitted from a DCP.
     * @param evt 
     */
    private void btn_confirmRejectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_confirmRejectMouseClicked
        dialog_rejectTime.dispose();
    }//GEN-LAST:event_btn_confirmRejectMouseClicked
    /**
     * This closes the editTime dialog box that appears when the Time Reviewer has 
     * finished writing any comments on why it has been changes and then clicks 
     * Accept.
     * @param evt 
     */
    private void btn_confirmTimeChangeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_confirmTimeChangeMouseClicked
        dialog_editTime.dispose();
    }//GEN-LAST:event_btn_confirmTimeChangeMouseClicked
    /**
     * This closes the editNote dialog box that would appear for the Note Reviewer
     * to make changes to the note that was submitted by a DCP. This button however
     * is no longer available on the Note Writing tab due to the Note Reviewer should
     * not have any reason to make any changes to a note. They should only be able to
     * reject or accept a note submission.
     * @param evt 
     */
    private void btn_confirmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_confirmMouseClicked
        dialog_editNote.dispose();
    }//GEN-LAST:event_btn_confirmMouseClicked
    /**
     * This clears the text fields that the user uses to change their 
     * password
     * @param evt 
     */
    private void btn_cancelNewPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelNewPassActionPerformed
        pass_enterOldPass.setText("");
        pass_enterNewPass.setText("");
        pass_confirmNewPass.setText("");
    }//GEN-LAST:event_btn_cancelNewPassActionPerformed
    /**
     * Removes a goal from the database that is linked to a participant
     *
     * @param evt
     */
    private void btn_goalRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_goalRemoveActionPerformed
//      JOptionPane.showMessageDialog(null, "Remove button currently not working! Please do not add more than 5 goals to a single Participant.");
        String selectedItem = (String) combo_goalGoalSelect.getSelectedItem();
        //UNCOMMENT ONCE READY TO GET THE REMOVE BUTTON WORKING ON THE GOAL PAGE
        int selectedOption = JOptionPane.showConfirmDialog(null,
                "Do you want to remove this goal?",
                "Remove goal?",
                JOptionPane.YES_NO_OPTION);
        if (selectedOption == JOptionPane.YES_OPTION) {
            //This is where one would add the code to remove a goal.
            try {
                GoalDBConnect removeGoal = new GoalDBConnect();
                int goalNum = combo_goalGoalSelect.getSelectedIndex();
                goalNum -= 1;
                ParticipantLogic p1 = new ParticipantLogic();
                ArrayList<GoalLogic> goalList = p1.gl.selectGoalsOrSomeShit(combo_goalPartSelect.getSelectedItem());
                p1.gl.removeGoal(goalList.get(goalNum).getGoalID());
                populateSavedNoteList(NoteDBConnect.getSavedDraft(activeUser));
                txt_goalDescription.setText("");
                txt_objectiveDescription.setText("");
                txt_guidanceNotes.setText("");
            } catch (SQLException ex) {
                Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
            }
//                JOptionPane.showMessageDialog(null, "Sorry- currently, goal"
//                        + " removal is not supported.");
        }
    }//GEN-LAST:event_btn_goalRemoveActionPerformed
    /**
     * Opens up a saved draft for the user under the "My Account" tab
     *
     * @param evt
     */
    private void btn_openNoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_openNoteActionPerformed
        try {
            String selectedNote = (String) combo_selectNoteToOpen.getSelectedItem();
            if ("Saved Notes...".equals(selectedNote)) {
                JOptionPane.showMessageDialog(null, "Select a draft to open.");
            } else {
                //this line check for admin or dcp on the save.
                if (loginTest == 1) {
                    System.out.println("woot woot");
                    noteID = Integer.parseInt(selectedNote.split(":")[0]);
                    NoteLogic note = NoteLogic.select(noteID);
                    tabPane_container.setSelectedIndex(0);
                    txt_noteContent.setText(note.getText());
                    for (int i = 1; i <= combo_noteChooseParticipant.getItemCount(); i++) {
                        String selectedItem = combo_noteChooseParticipant.getItemAt(i);
                        int selectedId = Integer.parseInt(selectedItem.split(":")[0]);
                        if (note.getParticipantID() == selectedId) {
                            combo_noteChooseParticipant.setSelectedIndex(i);
                            break;
                        }
                    }
                    note.getPl().getParticipantID();
                    //JOptionPane.showMessageDialog(null, "This function is not currently available.");
                    NoteDBConnect.getSavedDraft(activeUser);
                } else {
                    System.out.println("booyeah");

                    noteID = Integer.parseInt(selectedNote.split(":")[0]);
                    NoteLogic note = NoteLogic.select(noteID);
                    tabPane_container.setSelectedIndex(4);
                    txt_noteContent.setText(note.getText());
                    for (int i = 1; i <= combo_noteChooseParticipant.getItemCount(); i++) {
                        String selectedItem = combo_noteChooseParticipant.getItemAt(i);
                        int selectedId = Integer.parseInt(selectedItem.split(":")[0]);
                        if (note.getParticipantID() == selectedId) {
                            combo_noteChooseParticipant.setSelectedIndex(i);
                            break;
                        }
                    }
                    note.getPl().getParticipantID();
                    //JOptionPane.showMessageDialog(null, "This function is not currently available.");
                    NoteDBConnect.getSavedDraft(activeUser);
                }
                System.out.println(selectedNote);
                System.out.println("your note id: " + noteID);
                openDocTest = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_openNoteActionPerformed
    /**
     * Deletes a note that is in the saved draft portion of a users account.
     *
     * @param evt
     */
    private void btn_deleteNoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteNoteActionPerformed
        String selectedItem = (String) combo_selectNoteToOpen.getSelectedItem();

        if ("Saved Notes...".equals(selectedItem)) {
            JOptionPane.showMessageDialog(null, "Please select a note to delete.");
        } else if (!"Saved Notes...".equals(selectedItem)) {
            int selectedOption = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to delete the note?",
                    "Delete Note?",
                    JOptionPane.YES_NO_OPTION);
            if (selectedOption == JOptionPane.YES_OPTION) {
                try {
                    int selectedNoteID = Integer.parseInt(selectedItem.split(":")[0]);
                    NoteDBConnect.deleteDraft(selectedNoteID);
                    populateSavedNoteList(NoteDBConnect.getSavedDraft(activeUser));
                } catch (SQLException ex) {
                    Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //JOptionPane.showMessageDialog(null, "This function is not currently available.");
    }//GEN-LAST:event_btn_deleteNoteActionPerformed
    }
    /**
     * This is used when the user, most likely the DCP, goes the My Account tab
     * and uses the dropdown box to select a saved note to work on.
     * @param evt 
     */
    private void combo_selectNoteToOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_selectNoteToOpenActionPerformed
//        try {
//            
//            populateSavedNoteList(NoteDBConnect.getSavedDraft(activeUser));
//        } catch (SQLException ex) {
//            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_combo_selectNoteToOpenActionPerformed
    /**
     * This is called when the user clicks the dropdown box in the My Account tab
     * when they select a saved note.
     * @param evt 
     */
    private void combo_selectNoteToOpenFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_combo_selectNoteToOpenFocusGained
        try {
            populateSavedNoteList(NoteDBConnect.getSavedDraft(activeUser));
        } catch (SQLException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_combo_selectNoteToOpenFocusGained
    /**
     * 
     * @param evt 
     */
    private void combo_userLevelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_userLevelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_userLevelActionPerformed
    /**
     * This method is called when the user selects the logout option from the 
     * File tab at the top of the application.
     * @param evt 
     */
    private void item_logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_logoutActionPerformed
        this.dispose();
        activeUser = null;
        LoginForm login = new LoginForm();
        login.setVisible(true);
    }//GEN-LAST:event_item_logoutActionPerformed
    /**
     * This closes the entire application when the user selects the Exit option
     * from the File tab.
     * @param evt 
     */
    private void item_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_exitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_item_exitActionPerformed
    /**
     * This sets the text in the editNote dialog box for when the Note Reviewer 
     * would click the Edit button to edit the note that was submitted for review.
     * If this is correct than this button is no longer used due to the 
     * Edit button being removed from the Note Review tab.
     * @param evt 
     */
    private void btn_confirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_confirmActionPerformed
        txt_noteToBeReviewed.setText(txt_editNote.getText());
    }//GEN-LAST:event_btn_confirmActionPerformed
    /**
     * This method is called when the user clicks the Help tab and then selects 
     * the Documentation item. When clicked it will display a PDF version of a User
     * Guide that will help the user know the functions of the application and what
     * they can and cannot do based on their clearance level.
     * @param evt
     */
    private void item_documentationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_documentationActionPerformed
        if (Desktop.isDesktopSupported()) {
            try {
                String inputPdf = "UserGuide.pdf";
                InputStream manualAsStream = getClass().getClassLoader().getResourceAsStream(inputPdf);
                Path tempOutput = Files.createTempFile("UserGuide", ".pdf");

                tempOutput.toFile().deleteOnExit();
                Files.copy(manualAsStream, tempOutput, StandardCopyOption.REPLACE_EXISTING);
                File file = new File(tempOutput.toFile().getPath());
                if (file.exists()) {
                    Desktop.getDesktop().open(file);
                }

//        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
//        File myFile = new File(classLoader.getResource("UserGuide.pdf").getFile());
//        Desktop.getDesktop().open(myFile);
            } catch (IOException ex) {
                // no application registered for PDFs
            }
        }
    }//GEN-LAST:event_item_documentationActionPerformed
    /**
     * This is used when the Admin checks the box next to Seizure when they add
     * a new participant.
     * @param evt
     */
    private void check_seizureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_seizureActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_check_seizureActionPerformed
    /**
     * Calls the btn_uploadFileMouseClicked method
     * @param evt 
     */
    private void btn_uploadFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_uploadFileActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_uploadFileActionPerformed
    /**
     * Calls the btn_rejectMouseClicked method
     * @param evt 
     */
    private void btn_rejectNoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_rejectNoteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_rejectNoteActionPerformed
    /**
     * Calls the newNote() method when the DCP clicks the New Note button
     * in the Note Writing tab.
     * @param evt
     */
    private void btn_newNoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_newNoteActionPerformed
        newNote();
    }//GEN-LAST:event_btn_newNoteActionPerformed
    /**
     * This allows the database to save any comments that the Note Reviewer or DCP 
     * write to each other while submitting a note and rejecting a note.
     * @param evt 
     */
    private void txt_noteCommentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_noteCommentsMouseClicked
        // TODO add your handling code here:
        //System.out.println(list_selectNoteToReview);
        System.err.println(txt_noteComment.getText());
        //System.out.println(list_selectNoteToReview.getSelectedValue().split("||")[0]);
        String selectedNoteComment = txt_noteComment.getText();
        System.out.println("AdminForm.java, line 3529" + selectedNoteComment);
        //list_selectNoteToReview.getSe
        //System.err.println(selectedNoteID);
        String[] splitStr = selectedNoteComment.trim().split("-");
        selectedNoteComment = splitStr[0];
        System.err.println(Arrays.toString(splitStr));
        System.err.println(selectedNoteComment);
        System.out.println("my note comment check " + selectedNoteComment);
        if (!"NoteID".equals(selectedNoteComment)) {

            NoteLogic note = null;
            txt_noteComments.setText(txt_noteComment.getText());
            try {
                note = NoteLogic.select(Integer.parseInt(selectedNoteComment.trim()));
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            txt_noteComment.setText(note.getText());

            comment = note.getComment();
            System.out.println(comment);
//                if (!note.equals(null)){
//                    
//                }
        }

    }//GEN-LAST:event_txt_noteCommentsMouseClicked
    /**
     * This will sort the notes that are to be reviewed by the date the note was
     * submitted. This will need to be completed by the group that comes in after
     * Ctrl/Alt/Delicious.
     * @param evt 
     */
    private void btn_sortByDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sortByDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_sortByDateActionPerformed
    /**
     * This will sort the notes that are to be reviewed by the date the note was
     * submitted. This will need to be completed by the group that comes in after
     * Ctrl/Alt/Delicious.
     * @param evt 
     */
    private void btn_sortByUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sortByUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_sortByUserActionPerformed
    /**
     *  
     * @param evt 
     */
    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed
    /**
     * This method is called when the user clicks the Print button in the Print 
     * tab. This will allow the Admin to print a list of submitted notes to give 
     * to an Auditor.
     * @author Rupa Shrestha
     * @param evt 
     */
    private void btn_PrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_PrintActionPerformed
        if (txt_MedicaidID.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please enter a Medicaid ID before you try to print.",
                    "Missing Medicaid ID",JOptionPane.ERROR_MESSAGE);
        }
        else if (txt_StartDate.getDate() == null || txt_EndDate.getDate() == null){
            JOptionPane.showMessageDialog(null,"Please enter a start date and/or ending "
                    + "date to print the list of notes!","Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        else{
        String medID, start, end, location;
       Date startDate, endDate;
       int patID = 0;
       ParticipantLogic part = new ParticipantLogic();
       DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
       
       medID = txt_MedicaidID.getText();       
       startDate = txt_StartDate.getDate();
       endDate = txt_EndDate.getDate();
       start = df.format(startDate);
       end = df.format(endDate);
       System.out.println(medID);      
       System.out.println(start);
       System.out.println(end);
       
       if(jRadioButton1.isSelected()){
           location = jRadioButton1.getText();
       }else if(jRadioButton2.isSelected()){
           location = jRadioButton2.getText();
       }else{
           location = "you broke it how could you.";
       }
       
       System.out.println(location);
       
        try {
           part = part.selectMed(medID);
           patID = part.getParticipantID();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
        }
               System.out.println(patID);
       
       int selectedOption = JOptionPane.showConfirmDialog(null,
                    "Do you want to print notes for this patient?",
                    "Print notes?",
                    JOptionPane.YES_NO_OPTION);
       
        System.out.println(selectedOption);
       if(selectedOption == 0){
          ArrayList printList = new ArrayList();
       PrintLogic print = new PrintLogic();
        try {
            printList = print.getPrintList(location, patID, start, end);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        print.displayList(printList);
        
        try {
            print.printPrintList(printList);
        } catch (IOException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
        } 
       }  
    }//GEN-LAST:event_btn_PrintActionPerformed
    }
     /**
     * 
     * @param evt 
     */
    private void txt_MedicaidIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_MedicaidIDActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txt_MedicaidIDActionPerformed
    /**
     * 
     * @param evt 
     */
    private void txt_PatientNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_PatientNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_PatientNameActionPerformed
    /**
     * 
     * @param evt 
     */
    private void txt_userIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_userIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_userIDActionPerformed
    /**
     * This method is called when the user clicks the OK button. It is used as a
     * way to check if the Medicaid number that is put in pulls the correct
     * participant for whose notes are being printed.
     * @param evt 
     */
    private void btn_OKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_OKActionPerformed
        // TODO add your handling code here:

        if (!(txt_MedicaidID.getText().equals(""))) {
            String medID;
            ParticipantLogic part = new ParticipantLogic();
            medID = txt_MedicaidID.getText();
//                    int medId = Integer.parseInt(medID);
            int patID = part.getParticipantID();
            System.out.println(medID);
            try {

                part = part.selectMed(medID);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            txt_PatientName.setText(part.getParticipantFirstName() + " " + part.getParticipantLastName());

        } else{
            JOptionPane.showMessageDialog(null, "Please enter valid Medicaid ID!");
            System.out.println("Is it working?");

        }


    }//GEN-LAST:event_btn_OKActionPerformed
    /**
     * 
     * @param evt 
     */
    private void txt_participantIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_participantIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_participantIDActionPerformed
    /**
     * This method is called when the user clicks the Save button in the Print tab.
     * It allows the Admin to save the list of notes to either a directory on the 
     * computer or to a USB Drive.
     * @param evt 
     */
    private void btn_SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SaveActionPerformed
       
        if (txt_MedicaidID.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please enter a Medicaid ID before you try to save.",
                    "Missing Medicaid ID",JOptionPane.ERROR_MESSAGE);
        }
        else if (txt_StartDate.getDate() == null || txt_EndDate.getDate() == null){
            JOptionPane.showMessageDialog(null,"Please enter a start date and/or ending "
                    + "date to save the list of notes!","Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        else{        
       String medID, start, end, location;
       Date startDate, endDate;
       int patID = 0;
       ParticipantLogic part = new ParticipantLogic();
       DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
       
       medID = txt_MedicaidID.getText();       
       startDate = txt_StartDate.getDate();
       endDate = txt_EndDate.getDate();
       start = df.format(startDate);
       end = df.format(endDate);
       System.out.println(medID);      
       System.out.println(start);
       System.out.println(end);
       if(jRadioButton1.isSelected()){
           location = jRadioButton1.getText();
       }else if(jRadioButton2.isSelected()){
           location = jRadioButton2.getText();
       }else{
           location = "you broke it how could you.";
       }
       
       System.out.println(location);
        try {
           part = part.selectMed(medID);
           patID = part.getParticipantID();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
        }
               System.out.println(patID);
               
        int selectedOption = JOptionPane.showConfirmDialog(null,
                    "Do you want to save the notes for this patient?",
                    "Save notes?",
                    JOptionPane.YES_NO_OPTION);
       
        if(selectedOption == 0){
       ArrayList printList = new ArrayList();
       PrintLogic print = new PrintLogic();
        try {
            printList = print.getPrintList(location, patID, start, end);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        print.displayList(printList);
        try {
            print.savePrintList(printList);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_SaveActionPerformed
    }
    }        
     /**
     * This method is called when the user clicks the About tab under the Help
     * tab. It's purpose is to display a Message Dialog detailing information about
     * the application (Name of the Application, Version, Current team working on the
     * application, and which Semester the team is in.
     * @author Tyrus Skipper
     * @param evt 
     */
    private void item_aboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_aboutActionPerformed
        System.out.println("You are at the about section.");

        String about = ("           DeRiche Supernote\n"
                + "   Current Version: " + version + "\n"
                + "   Current Team: " + teamName + "\n"
                + "   CIST 2931\n"
                + "   Semester: " + semester);
        JOptionPane.showMessageDialog(null, about, "About the application", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_item_aboutActionPerformed
    /**
     * 
     * @param evt 
     */
    private void btn_displayCalendarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_displayCalendarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_displayCalendarActionPerformed

    private void check_formCCAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_formCCAActionPerformed
        if (Desktop.isDesktopSupported()) {
            try {
                String inputPdf = "Community Connection Activity Sheet.xlsx";
                InputStream manualAsStream = getClass().getClassLoader().getResourceAsStream(inputPdf);
                Path tempOutput = Files.createTempFile("Community Connection Activity Sheet", ".xlsx");
 
                tempOutput.toFile().deleteOnExit();
                Files.copy(manualAsStream, tempOutput, StandardCopyOption.REPLACE_EXISTING);
                File file = new File(tempOutput.toFile().getPath());
                if (file.exists()) {
                    Desktop.getDesktop().open(file);
                }
 
            } catch (IOException ex) {
               
            }
        }
    }//GEN-LAST:event_check_formCCAActionPerformed

    private void check_formBloodPressureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_formBloodPressureActionPerformed
        if (Desktop.isDesktopSupported()) {
            try {
                String inputPdf = "weightBPchart.docx";
                InputStream manualAsStream = getClass().getClassLoader().getResourceAsStream(inputPdf);
                Path tempOutput = Files.createTempFile("weightBPchart", ".docx");
 
                tempOutput.toFile().deleteOnExit();
                Files.copy(manualAsStream, tempOutput, StandardCopyOption.REPLACE_EXISTING);
                File file = new File(tempOutput.toFile().getPath());
                if (file.exists()) {
                    Desktop.getDesktop().open(file);
                }
 
            } catch (IOException ex) {
               
            }
        }
    }//GEN-LAST:event_check_formBloodPressureActionPerformed

    private void check_formBodyCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_formBodyCheckActionPerformed
        if (Desktop.isDesktopSupported()) {
            try {
                String inputPdf = "body check newest.docx";
                InputStream manualAsStream = getClass().getClassLoader().getResourceAsStream(inputPdf);
                Path tempOutput = Files.createTempFile("body check newest", ".docx");
 
                tempOutput.toFile().deleteOnExit();
                Files.copy(manualAsStream, tempOutput, StandardCopyOption.REPLACE_EXISTING);
                File file = new File(tempOutput.toFile().getPath());
                if (file.exists()) {
                    Desktop.getDesktop().open(file);
                }
 
            } catch (IOException ex) {
               
            }
        }
    }//GEN-LAST:event_check_formBodyCheckActionPerformed
/**
 * We do not have the file for this checkbox so for now it does nothing. You need
 * to get the Bowel Movement from from DeRiche.
 * @param evt 
 */
    private void check_formBowelMovementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_formBowelMovementActionPerformed
//        if (Desktop.isDesktopSupported()) {
//            try {
//                String inputPdf = ".xlsx";
//                InputStream manualAsStream = getClass().getClassLoader().getResourceAsStream(inputPdf);
//                Path tempOutput = Files.createTempFile("Repositioning Log", ".xlsx");
// 
//                tempOutput.toFile().deleteOnExit();
//                Files.copy(manualAsStream, tempOutput, StandardCopyOption.REPLACE_EXISTING);
//                File file = new File(tempOutput.toFile().getPath());
//                if (file.exists()) {
//                    Desktop.getDesktop().open(file);
//                }
// 
//            } catch (IOException ex) {
//               
//            }
//        }
    }//GEN-LAST:event_check_formBowelMovementActionPerformed

    private void check_formRepositionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_formRepositionActionPerformed
        if (Desktop.isDesktopSupported()) {
            try {
                String inputPdf = "Repositioning Log.xlsx";
                InputStream manualAsStream = getClass().getClassLoader().getResourceAsStream(inputPdf);
                Path tempOutput = Files.createTempFile("Repositioning Log", ".xlsx");
 
                tempOutput.toFile().deleteOnExit();
                Files.copy(manualAsStream, tempOutput, StandardCopyOption.REPLACE_EXISTING);
                File file = new File(tempOutput.toFile().getPath());
                if (file.exists()) {
                    Desktop.getDesktop().open(file);
                }
 
            } catch (IOException ex) {
               
            }
        }
    }//GEN-LAST:event_check_formRepositionActionPerformed

    private void check_formIncidentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_formIncidentActionPerformed
        if (Desktop.isDesktopSupported()) {
            try {
                String inputPdf = "Internal_Incident form 2015.docx";
                InputStream manualAsStream = getClass().getClassLoader().getResourceAsStream(inputPdf);
                Path tempOutput = Files.createTempFile("Internal_Incident form", ".docx");
 
                tempOutput.toFile().deleteOnExit();
                Files.copy(manualAsStream, tempOutput, StandardCopyOption.REPLACE_EXISTING);
                File file = new File(tempOutput.toFile().getPath());
                if (file.exists()) {
                    Desktop.getDesktop().open(file);
                }
 
            } catch (IOException ex) {
               
            }
        }
    }//GEN-LAST:event_check_formIncidentActionPerformed

    private void check_formSeizureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_formSeizureActionPerformed
        if (Desktop.isDesktopSupported()) {
            try {
                String inputPdf = "Seizure Activity checklist.xlsx";
                InputStream manualAsStream = getClass().getClassLoader().getResourceAsStream(inputPdf);
                Path tempOutput = Files.createTempFile("Seizure Activity checklist", ".xlsx");
 
                tempOutput.toFile().deleteOnExit();
                Files.copy(manualAsStream, tempOutput, StandardCopyOption.REPLACE_EXISTING);
                File file = new File(tempOutput.toFile().getPath());
                if (file.exists()) {
                    Desktop.getDesktop().open(file);
                }
 
            } catch (IOException ex) {
               
            }
        }
    }//GEN-LAST:event_check_formSeizureActionPerformed

    private void check_formAbsenceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_formAbsenceActionPerformed
        if (Desktop.isDesktopSupported()) {
            try {
                String inputPdf = "Single Absence.docx";
                InputStream manualAsStream = getClass().getClassLoader().getResourceAsStream(inputPdf);
                Path tempOutput = Files.createTempFile("Single Absence", ".docx");
 
                tempOutput.toFile().deleteOnExit();
                Files.copy(manualAsStream, tempOutput, StandardCopyOption.REPLACE_EXISTING);
                File file = new File(tempOutput.toFile().getPath());
                if (file.exists()) {
                    Desktop.getDesktop().open(file);
                }
 
            } catch (IOException ex) {
               
            }
        }
    }//GEN-LAST:event_check_formAbsenceActionPerformed
    /**
     * 
     * @param evt 
     */
    private void txt_userTitleActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
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
            java.util.logging.Logger.getLogger(AdminForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminForm(activeUser).setVisible(true);
                System.out.println("ACTIVE USER IS: " + activeUser);
//tabPane_container.setEnabledAt(6, false);
            }
        });
    }

    /**
     * Populates all JComboBoxes that need to be populated with a list of users
     *
     * @param list
     */
    public void populateComboSelectUser(ArrayList list) {

        try {
            combo_selectUser.removeAllItems();
            combo_selectUser.addItem("Select User Or Create New");
            Object[] userArray = UserDBConnect.populateUser().toArray();
            String user;
            for (Object userArray1 : userArray) {
                //as long as i is less than the length of the array
                user = (String) userArray1;
                //String[] array = user.split("-");
                String[] comboBoxArr = new String[3];
                comboBoxArr = user.split("-"); //splitting element i from the array into a seperate array by the delimeter of "-"
                String output = "";
                for (int u = 0; u < comboBoxArr.length; u++) {
                    if (u != 1 && u != 2 && u != 4) {
                        output += comboBoxArr[u] + " ";
                    }
                }
                combo_selectUser.addItem(output);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * populates every combo box and JList that needs to be populated with a
     * list of Participants
     *
     * @param list
     */
    public void populateComboParticipant(ArrayList<ParticipantLogic> list) {
        //three different lists populated with this method
        combo_participantSelect.removeAllItems();
        combo_goalPartSelect.removeAllItems();
        combo_noteChooseParticipant.removeAllItems();

        combo_participantSelect.addItem("Select A Participant");
        combo_goalPartSelect.addItem("Select A Participant");
        combo_noteChooseParticipant.addItem("Select A Participant");
        String participantInfo;
        System.out.println(list.size());
        for (ParticipantLogic list1 : list) {
            participantInfo = list1.getParticipantID() + ": " + list1.getParticipantFirstName() + list1.getParticipantLastName();
            combo_participantSelect.addItem(participantInfo);
            combo_goalPartSelect.addItem(participantInfo);
            combo_noteChooseParticipant.addItem(participantInfo);
        }
    }

    /**
     * populates every JList that needs a list of Notes pending review
     *
     * @param list
     */
    public void populateParticipantNoteReviewJList(List<NoteLogic> list) {
        //list_selectNoteToReview = new JList(reviewModel);
        String delimiter = " - ";
        reviewModel.clear();
        reviewModel.addElement("NoteID - Date Submitted - User who submitted - Participant");
        for (int i = 0; i < list.size(); i++) {
            String noteID = Integer.toString(list.get(i).getNoteID());
            Timestamp submittedTime = list.get(i).getTimeSubmitted();
            String date = new SimpleDateFormat("MM/dd/yyyy").format(submittedTime);
            String submittedUser = list.get(i).getUl().getFirstName() + " " + list.get(i).getUl().getLastName();

            String participantID = Integer.toString(list.get(i).getPl().getParticipantID());
            String fName = list.get(i).getPl().getParticipantFirstName();
            String lName = list.get(i).getPl().getParticipantLastName();
            String participant = participantID + ":" + fName + " " + lName;
            //reviewModel.add(i=+1, noteID+" || "+date + " || " + submittedUser + " || " + participant);
            reviewModel.addElement(noteID + delimiter + date + delimiter + submittedUser + delimiter + participant);
            //reviewModel.addElement(noteID+" || "+date + " || " + submittedUser + " || " + participant);
        }
    }

    /**
     * populates every JList that needs to have a list of participants
     *
     * @param list
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void populateParticipantJList(ArrayList<ParticipantLogic> list) throws SQLException, ClassNotFoundException {
        list_timeReviewParticipant = new JList(participantModel);
        participantModel.clear();
        for (ParticipantLogic list1 : list) {
            participantModel.addElement(list1.getParticipantID() + ": " + list1.getParticipantFirstName());
        }
    }

    /**
     *  This method populates the dropdown box that shows the list of goals 
     * for a participant.
     * @param list
     */
    public void populateComboGoal(ArrayList<GoalLogic> list) {
        System.out.println(list.size());
        if (list.size() > 0 && !"empty".equals(list.get(0).getDescription())) {
            combo_goalGoalSelect.removeAllItems();
            combo_goalGoalSelect.addItem("New Goal");
            for (int i = 1; i <= list.size(); i++) {

                combo_goalGoalSelect.addItem("Goal-" + i);
            }
        } else {
            combo_goalGoalSelect.removeAllItems();
            combo_goalGoalSelect.addItem("Select A Goal");
        }
    }

    /**
     * Used to initialize the combo boxes that list goals
     */
    public void populateComboGoal() {
        combo_goalGoalSelect.removeAllItems();
        combo_goalGoalSelect.addItem("Select A Goal");
    }

    /**
     * This method is called when the New Note button is pressed in the Note 
     * Writing tab. This will allow the user to save or submit a new note so it 
     * does not overwrite the previous note that was saved or submitted.
     * Created by Steven Alcorn
     */
    public void newNote() {
        System.out.println("You are at the new note.");
        System.out.println(openDocTest);
        openDocTest = false;
        System.out.println(openDocTest);
        noteID = -1;
        txt_noteContent.setText(null);
        txt_goalDescription1.setText(null);
        txt_goalDescription2.setText(null);
        txt_goalDescription3.setText(null);
        txt_goalDescription4.setText(null);
        txt_goalDescription5.setText(null);
        combo_noteChooseParticipant.setSelectedIndex(0);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btnGroup_yesNo1;
    private javax.swing.ButtonGroup btnGroup_yesNo2;
    private javax.swing.ButtonGroup btnGroup_yesNo3;
    private javax.swing.ButtonGroup btnGroup_yesNo4;
    private javax.swing.ButtonGroup btnGroup_yesNo5;
    private javax.swing.JButton btn_OK;
    private javax.swing.JButton btn_Print;
    private javax.swing.JButton btn_Save;
    private javax.swing.JButton btn_acceptAddComments;
    private javax.swing.JButton btn_addParticipant;
    private javax.swing.JButton btn_appendGoal1;
    private javax.swing.JButton btn_appendGoal2;
    private javax.swing.JButton btn_appendGoal3;
    private javax.swing.JButton btn_appendGoal4;
    private javax.swing.JButton btn_appendGoal5;
    private javax.swing.JButton btn_approveNote;
    private javax.swing.JButton btn_approveTime;
    private javax.swing.JButton btn_cancel;
    private javax.swing.JButton btn_cancelComments;
    private javax.swing.JButton btn_cancelNewPass;
    private javax.swing.JButton btn_cancelReject;
    private javax.swing.JButton btn_cancelTimeChange;
    private javax.swing.JButton btn_confirm;
    private javax.swing.JButton btn_confirmNewPass;
    private javax.swing.JButton btn_confirmReject;
    private javax.swing.JButton btn_confirmTimeChange;
    private javax.swing.JButton btn_deleteNote;
    private javax.swing.JButton btn_deleteUser;
    private javax.swing.JButton btn_displayCalendar;
    private javax.swing.JButton btn_downloadFiles;
    private javax.swing.JButton btn_editTime;
    private javax.swing.JButton btn_goalAdd;
    private javax.swing.JButton btn_goalRemove;
    private javax.swing.JButton btn_goalUpdate;
    private javax.swing.JButton btn_newNote;
    private javax.swing.JButton btn_newUser;
    private javax.swing.JButton btn_openNote;
    private javax.swing.JButton btn_rejectNote;
    private javax.swing.JButton btn_rejectTime;
    private javax.swing.JButton btn_saveNote;
    private javax.swing.JButton btn_sortByDate;
    private javax.swing.JButton btn_sortByParticipant;
    private javax.swing.JButton btn_sortByUser;
    private javax.swing.JButton btn_submitCancel;
    private javax.swing.JButton btn_submitNote;
    private javax.swing.JButton btn_submitSubmit;
    private javax.swing.JButton btn_updateParticipant;
    private javax.swing.JButton btn_updateUser;
    private javax.swing.JButton btn_uploadFile;
    private javax.swing.JButton btn_viewUser;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox check_bm;
    private javax.swing.JCheckBox check_formAbsence;
    private javax.swing.JCheckBox check_formBloodPressure;
    private javax.swing.JCheckBox check_formBodyCheck;
    private javax.swing.JCheckBox check_formBowelMovement;
    private javax.swing.JCheckBox check_formCCA;
    private javax.swing.JCheckBox check_formIncident;
    private javax.swing.JCheckBox check_formNone;
    private javax.swing.JCheckBox check_formReposition;
    private javax.swing.JCheckBox check_formSeizure;
    private javax.swing.JCheckBox check_medicaid;
    private javax.swing.JCheckBox check_repositioning;
    private javax.swing.JCheckBox check_seizure;
    private javax.swing.JCheckBox check_selfPay;
    private javax.swing.JComboBox<String> combo_frequency;
    private javax.swing.JComboBox<String> combo_goalGoalSelect;
    private javax.swing.JComboBox<String> combo_goalPartSelect;
    private javax.swing.JComboBox<String> combo_interval;
    private javax.swing.JComboBox<String> combo_noteChooseParticipant;
    private javax.swing.JComboBox<String> combo_participantSelect;
    private javax.swing.JComboBox<String> combo_promptingLevel1;
    private javax.swing.JComboBox<String> combo_promptingLevel2;
    private javax.swing.JComboBox<String> combo_promptingLevel3;
    private javax.swing.JComboBox<String> combo_promptingLevel4;
    private javax.swing.JComboBox<String> combo_promptingLevel5;
    private javax.swing.JComboBox<String> combo_selectNoteToOpen;
    private javax.swing.JComboBox<String> combo_selectUser;
    private javax.swing.JComboBox<String> combo_userLevel;
    private javax.swing.JDialog dialog_addComments;
    private javax.swing.JDialog dialog_downloadFile;
    private javax.swing.JDialog dialog_editNote;
    private javax.swing.JDialog dialog_editTime;
    private javax.swing.JDialog dialog_rejectTime;
    private javax.swing.JDialog dialog_submitNote;
    private javax.swing.JDialog dialog_uploadFile;
    private javax.swing.JFileChooser file_download;
    private javax.swing.JFileChooser file_upload;
    private javax.swing.JMenuItem item_about;
    private javax.swing.JMenuItem item_documentation;
    private javax.swing.JMenuItem item_exit;
    private javax.swing.JMenuItem item_logout;
    private javax.swing.JMenuItem item_preferences;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jsp_goalDescription1;
    private javax.swing.JScrollPane jsp_goalDescription2;
    private javax.swing.JScrollPane jsp_goalDescription3;
    private javax.swing.JScrollPane jsp_goalDescription4;
    private javax.swing.JScrollPane jsp_goalDescription5;
    private javax.swing.JLabel lbl_EndDate;
    private javax.swing.JLabel lbl_MedicaidID;
    private javax.swing.JLabel lbl_PatientName;
    private javax.swing.JLabel lbl_StartDate;
    private javax.swing.JLabel lbl_addComments;
    private javax.swing.JLabel lbl_confirmNewPassword;
    private javax.swing.JLabel lbl_downloadFiles;
    private javax.swing.JLabel lbl_editTime;
    private javax.swing.JLabel lbl_enterNewPassword;
    private javax.swing.JLabel lbl_enterOldPassword;
    private javax.swing.JLabel lbl_frequency;
    private javax.swing.JLabel lbl_goalDescription;
    private javax.swing.JLabel lbl_goalPromptLevels;
    private javax.swing.JLabel lbl_guidanceNotes;
    private javax.swing.JLabel lbl_insurance;
    private javax.swing.JLabel lbl_interval;
    private javax.swing.JLabel lbl_listOfTimes;
    private javax.swing.JLabel lbl_location;
    private javax.swing.JLabel lbl_mandatoryForm;
    private javax.swing.JLabel lbl_medicaidNumber;
    private javax.swing.JLabel lbl_noteChooseParticipant;
    private javax.swing.JLabel lbl_noteLabel;
    private javax.swing.JLabel lbl_noteSubmissionInstructions;
    private javax.swing.JLabel lbl_noteSubmitFurtherInstructions;
    private javax.swing.JLabel lbl_noteToBeReviewed;
    private javax.swing.JLabel lbl_objective;
    private javax.swing.JLabel lbl_participantFirstName;
    private javax.swing.JLabel lbl_participantLastName;
    private javax.swing.JLabel lbl_previousNotes;
    private javax.swing.JLabel lbl_rejectTime;
    private javax.swing.JLabel lbl_selectNoteToOpen;
    private javax.swing.JLabel lbl_sortNotesBy;
    private javax.swing.JTextField lbl_todayDate;
    private javax.swing.JLabel lbl_uploadFiles;
    private javax.swing.JLabel lbl_userFirstName;
    private javax.swing.JLabel lbl_userLastName;
    private javax.swing.JLabel lbl_userLevel;
    private javax.swing.JLabel lbl_userPass;
    private javax.swing.JLabel lbl_userTitle;
    private javax.swing.JLabel lbl_username;
    private javax.swing.JList<String> list_selectNoteToReview;
    private javax.swing.JList<String> list_timeReviewParticipant;
    private javax.swing.JMenuBar menuBar_adminForm;
    private javax.swing.JMenu menu_edit;
    private javax.swing.JMenu menu_file;
    private javax.swing.JMenu menu_help;
    private javax.swing.JPanel panel_fileManaging;
    private javax.swing.JPanel panel_files;
    private javax.swing.JPanel panel_goal01;
    private javax.swing.JPanel panel_goal02;
    private javax.swing.JPanel panel_goal03;
    private javax.swing.JPanel panel_goal04;
    private javax.swing.JPanel panel_goal05;
    private javax.swing.JPanel panel_goals;
    private javax.swing.JPanel panel_myAccount;
    private javax.swing.JPanel panel_mySavedNotes;
    private javax.swing.JPanel panel_noteReview;
    private javax.swing.JPanel panel_noteWriting;
    private javax.swing.JPanel panel_noteWritingGoals;
    private javax.swing.JPanel panel_participantGoals;
    private javax.swing.JPanel panel_participants;
    private javax.swing.JPanel panel_timeReview;
    private javax.swing.JPanel panel_updatePassword;
    private javax.swing.JPanel panel_users;
    private javax.swing.JPasswordField pass_confirmNewPass;
    private javax.swing.JPasswordField pass_enterNewPass;
    private javax.swing.JPasswordField pass_enterOldPass;
    private javax.swing.JRadioButton radio_goal1No;
    private javax.swing.JRadioButton radio_goal1Yes;
    private javax.swing.JRadioButton radio_goal2No;
    private javax.swing.JRadioButton radio_goal2Yes;
    private javax.swing.JRadioButton radio_goal3No;
    private javax.swing.JRadioButton radio_goal3Yes;
    private javax.swing.JRadioButton radio_goal4No;
    private javax.swing.JRadioButton radio_goal4Yes;
    private javax.swing.JRadioButton radio_goal5No;
    private javax.swing.JRadioButton radio_goal5Yes;
    private javax.swing.JScrollPane scroll_editNote;
    private javax.swing.JScrollPane scroll_noteComment;
    private javax.swing.JScrollPane scroll_noteToBeReviewed;
    private javax.swing.JScrollPane scroll_rejectNoteComment;
    private javax.swing.JScrollPane scroll_rejectedTime;
    private javax.swing.JScrollPane scroll_selectNoteToReview;
    private javax.swing.JScrollPane scroll_timeReviewNote;
    private javax.swing.JScrollPane scroll_timeReviewParticipant;
    private javax.swing.JTabbedPane tabPane_container;
    private com.toedter.calendar.JDateChooser txt_EndDate;
    private javax.swing.JTextField txt_MedicaidID;
    private javax.swing.JTextField txt_PatientName;
    private com.toedter.calendar.JDateChooser txt_StartDate;
    private javax.swing.JTextArea txt_editNote;
    private javax.swing.JTextArea txt_goalDescription;
    private javax.swing.JTextArea txt_goalDescription1;
    private javax.swing.JTextArea txt_goalDescription2;
    private javax.swing.JTextArea txt_goalDescription3;
    private javax.swing.JTextArea txt_goalDescription4;
    private javax.swing.JTextArea txt_goalDescription5;
    private javax.swing.JTextArea txt_guidanceNotes;
    private javax.swing.JTextArea txt_noteComment;
    private javax.swing.JTextArea txt_noteComments;
    private javax.swing.JTextArea txt_noteContent;
    private javax.swing.JTextArea txt_noteToBeReviewed;
    private javax.swing.JTextArea txt_objectiveDescription;
    private javax.swing.JTextField txt_participantFirstName;
    private javax.swing.JTextField txt_participantID;
    private javax.swing.JTextField txt_participantLastName;
    private javax.swing.JTextArea txt_rejectNoteComment;
    private javax.swing.JTextArea txt_rejectedTime;
    private javax.swing.JTextArea txt_timeReviewNote;
    private javax.swing.JTextField txt_userFirstName;
    private javax.swing.JTextField txt_userID;
    private javax.swing.JTextField txt_userLastName;
    private javax.swing.JTextField txt_userPass;
    private javax.swing.JTextField txt_userTitle;
    // End of variables declaration//GEN-END:variables
}
