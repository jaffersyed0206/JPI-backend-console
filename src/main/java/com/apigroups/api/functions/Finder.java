package com.apigroups.api.functions;

import java.util.List;

import com.apigroups.api.requests.Projectnotes;
import com.apigroups.api.requests.Projects;


public class Finder {

    public static Projects findProject(List<Projects> projectarray , String api) {
        for (Projects doc : projectarray) {
            if (doc.getProjectapi().equals(api) == true) {
              return doc;
            }
          }
          return null;
    }
    public static Projectnotes findProjectNotes(List<Projectnotes> currentnote, String noteid) {
        for (Projectnotes docs : currentnote) {
          if (docs.getNoteid().equals(noteid) == true) {
            return docs;
          }
        }
        return null;
      }
    public static Projects findProjectByApiKey(List<Projects> projectarray, String apikey) {
      for (Projects docs: projectarray) {
        return docs;
      }
      return null;
    }
    
    
}