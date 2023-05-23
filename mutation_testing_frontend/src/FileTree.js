import React, { useState, useEffect } from "react";
import { makeStyles } from "@material-ui/core/styles";
import TreeView from "@material-ui/lab/TreeView";
import TreeItem from "@material-ui/lab/TreeItem";
import FolderIcon from "@material-ui/icons/Folder";
import FolderOpenIcon from "@material-ui/icons/FolderOpen";
import FileIcon from "@material-ui/icons/InsertDriveFile";

const useStyles = makeStyles({
  root: {
    height: 240,
    flexGrow: 1,
    maxWidth: 400,
  },
});

function FileTree() {
  const classes = useStyles();
  const [data, setData] = useState([]);

  useEffect(() => {
    async function fetchData() {
      //let response = await fetch("/api/files");
      //let json = await response.json();
      let json = [
        {
          name: "Documents",
          type: "folder",
          children: [
            {
              name: "Resume.pdf",
              type: "file",
            },
            {
              name: "Work",
              type: "folder",
              children: [
                {
                  name: "Presentation.pptx",
                  type: "file",
                },
                {
                  name: "Reports",
                  type: "folder",
                  children: [
                    {
                      name: "Q1.pdf",
                      type: "file",
                    },
                    {
                      name: "Q2.pdf",
                      type: "file",
                    },
                  ],
                },
              ],
            },
          ],
        },
      ];
      setData(json);
    }
    fetchData();
  }, []);

  const handleClick = (event, nodeIds) => {
    let path = "";
    let currentNode = data.find((node) => node.name === nodeIds[0]);
    for (let i = 1; i < nodeIds.length; i++) {
      const childNode = currentNode.children.find(
        (node) => node.name === nodeIds[i]
      );
      if (!childNode) break;
      currentNode = childNode;
      path += currentNode.name + "/";
    }
    if (currentNode && currentNode.type === "file") {
      path = path.slice(0, -1);
      console.log(`Clicked node with path: ${path}`);
    } else {
      console.error(`Node with path ${nodeIds.join("/")} not found`);
    }
  };

  const renderTree = (nodes) => (
    <TreeItem
      key={nodes.name}
      nodeId={nodes.name}
      label={nodes.name}
      icon={nodes.type === "folder" ? <FolderIcon /> : <FileIcon />}
      onClick={(event) => handleClick(event, [nodes.name])}
    >
      {Array.isArray(nodes.children)
        ? nodes.children.map((node) => renderTree(node))
        : null}
    </TreeItem>
  );

  return (
    <div className={classes.root}>
      <TreeView
        defaultCollapseIcon={<FolderIcon />}
        defaultExpandIcon={<FolderOpenIcon />}
      >
        {data.map((node) => renderTree(node))}
      </TreeView>
    </div>
  );
}

export default FileTree;
