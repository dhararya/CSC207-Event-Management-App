package MessagingPresenters;

import java.util.ArrayList;

public abstract class MessengerPresenter {
    public void printMenu() {
        printWelcomeMessage();
        printGoBack();
        System.out.println("Press 1 to view all of your individual conversations" + System.lineSeparator() +
                "Press 2 to view all of your group chats");
        printMessagesMenu();
    }

    public void printGoBack() {
        System.out.println("Press 0 to go back");
    }

    public abstract void printWelcomeMessage();

    public abstract void printMessagesMenu();

    public void askForEmail() {System.out.println("Please enter the recipient's email address or 0 to go back:");}

    public void askForMessageBody() {System.out.println("Do not use ';'. Please enter the content of your text or 0 to go back:");}

    public void printMessageSentSuccess() {System.out.println("Message successfully sent!" + System.lineSeparator());}

    public void printQuitMessage() {System.out.println("Quitting messenger client" + System.lineSeparator());}

    public void printSendMessageError() {System.out.println("Email address not found or access denied. Please try again or press 0 to go back." + System.lineSeparator());}

    public void printInvalidOptionError() {System.out.println("Invalid option. Try again." + System.lineSeparator());}

    /**
     * Prints the emails this user has messaged or has been messaged by
     *
     * @param emails an ArrayList containing Strings representing emails
     */

    public void viewChats(ArrayList<String> emails) {
        if (emails.size() == 0) {
            System.out.println("No chats found");
        } else {
            int i = 1;
            for (String email : emails) {
                System.out.println(i + " - " + email);
                i++;
            }
            System.out.println("Input the number corresponding to the email address with the conversation you wish " +
                    "to view or 0 to go back:");
        }
    }

    public void viewGroupChats(ArrayList<String> talkIDs) {
        if (talkIDs.size() == 0) {
            System.out.println("No chats found");
        } else {
            int i = 1;
            for (String talkID : talkIDs) {
                System.out.println(i + " - " + "Group Chat with Event ID: "+ talkID);
                i++;
            }
            System.out.println("Input the number corresponding to the email address with the conversation you wish " +
                    "to view or 0 to go back:");
        }
    }

    /**
     * Prints a conversation.
     *
     * @param messages an ArrayList containing messages sent to or by this user.
     */

    public void viewConversation(ArrayList<Message> messages, Boolean viewingArchivedMessages) {
        if (viewingArchivedMessages) {
            System.out.println("Viewing archived messages. Press 'a' to view unarchived messages");
        }
        else {
            System.out.println("Viewing unarchived messages. Enter 'a' to view archived messages");
        }
        if (messages.size() == 0) {
            System.out.println("Nothing to see here!");
        } else {
            for (Message message : messages) {
                System.out.println(message.getSenderEmail() + ": " + message.getMessageContent());
            }
        }
        System.out.println(System.lineSeparator() + "Press 0 to go back.");
    }

    public void viewGroupChat(ArrayList<String> messages){
        if (messages.size() == 0) {
            System.out.println("Nothing to see here!");
            System.out.println(System.lineSeparator() + "Press 0 to go back.");
        } else {
            for (String message : messages) {
                System.out.println(message);
            }
            System.out.println(System.lineSeparator() + "Press 0 to go back.");
        }
    }
}
