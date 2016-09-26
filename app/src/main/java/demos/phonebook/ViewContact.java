package demos.phonebook;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import demos.phonebook.db.Contact;
import demos.phonebook.db.ContactHelper;

public class ViewContact extends AppCompatActivity {

    private int contact_id;
    private TextView name, organization, phone, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);

        name = (TextView) findViewById(R.id.tv_name);
        organization = (TextView) findViewById(R.id.tv_organization);
        phone = (TextView) findViewById(R.id.tv_phone);
        address = (TextView) findViewById(R.id.tv_address);

    }

    @Override
    protected void onResume() {

        Bundle bundle = getIntent().getExtras();
        contact_id = bundle.getInt("contact_id");

        viewContact(contact_id);

        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.view_contact_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.menu_view_contact:
                Bundle bundle = new Bundle();
                bundle.putInt("contact_id", contact_id);
                Intent updateContactActivity = new Intent(ViewContact.this, EditContact.class);
                updateContactActivity.putExtras(bundle);
                startActivity(updateContactActivity);
                break;

            case R.id.menu_delete_contact:
                /* Delete contact -> load phonebook activity */
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    private void viewContact(int id) {

        ContactHelper db = new ContactHelper(this);
        Contact contact = db.getContact(id);

        name.setText(contact.getName());
        organization.setText(contact.getOrganization());
        phone.setText(contact.getPhone());
        address.setText(contact.getAddress());

    }

}
